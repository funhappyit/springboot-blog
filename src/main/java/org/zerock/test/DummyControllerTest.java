package org.zerock.test;


import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {//key=value
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
	//{id} 주소로 파라레터를 전달 받을 수 있음 
	// http://localhost:8080/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		/*
		  user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		  그럼 return null이 리턴이 되자나... 그럼 프로그램에 문제가 있지 않겠니?
		  Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!!
		 */
		
		
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				// TODO Auto-generated method stub
//				return new IllegalArgumentException("해당 유저는 없습니다.id:"+id);
//			}
//		});
		
		//람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다");
		});
		
		//요청: 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json
		//스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동 
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서 
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return user;
	}
	
	
}
