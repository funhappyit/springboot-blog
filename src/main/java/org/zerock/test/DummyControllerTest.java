package org.zerock.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.repository.UserRepository;

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
}
