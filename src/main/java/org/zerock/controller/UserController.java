package org.zerock.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.zerock.config.auth.PrincipalDetail;
import org.zerock.model.KakaoProfile;
import org.zerock.model.OAuthToken;
import org.zerock.model.User;
import org.zerock.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/* 혀용 
//그냥 주소가 /이면 index.jsp만 허용 
//static이하에 있는 /js/** , /css/** , /image/**

@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "user/updateForm";
	}
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody RedirectView kakaoCallback(String code) {//Data를 리턴해주는 컨틀롤러 함수 
		
		//POST방식으로 key=value 데이터를 요청(카카오쪽으로)
		//Retrofit2
		//OkHttp
		//RestTemplate
	
		RestTemplate rt = new RestTemplate();
		//HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpBody 오브젝트 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "fabd23473efc0faabd913cffe63733e8");
		params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers);
		
		//Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받기
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);
		//Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		
		RestTemplate rt2 = new RestTemplate();
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2);
		
		//Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받기
		ResponseEntity<String> response2 = rt2.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoProfileRequest2,
			String.class
		);
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		//User 오브젝트 : username, password, email
		System.out.println("카카오 아이디(번호):"+kakaoProfile.getId());
		System.out.println("카카오 이메일:"+kakaoProfile.getKakao_account().getEmail());
		System.out.println("블로그 서버 유저 네임"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
		//UUID란 -> 중복되지 않은 어떤 특정 값을 만들어내는 알고리즘
		System.out.println("블로그 서버 패스워드: "+cosKey);
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		//가입자 혹은 비가입자 체크 해서 처리 
		User originUser = userService.selectUser(kakaoUser.getUsername());
		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아닙니다.....");
			userService.save(kakaoUser);
		}
		//로그인 처리 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
				
		
		return new RedirectView("/");
	
	}
	
	
}
