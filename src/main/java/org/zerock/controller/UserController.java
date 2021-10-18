package org.zerock.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.zerock.config.auth.PrincipalDetail;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/* 혀용 
//그냥 주소가 /이면 index.jsp만 허용 
//static이하에 있는 /js/** , /css/** , /image/**

@Controller
public class UserController {
	
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
	public @ResponseBody String kakaoCallback(String code) {//Data를 리턴해주는 컨틀롤러 함수 
		
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
		
		return "인증 완료:코드값"+response;
	}
	
	
}
