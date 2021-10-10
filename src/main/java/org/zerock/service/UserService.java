package org.zerock.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.Ioc를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void save(User user) {
		String rawPassword = user.getPassword(); //1234 원문
		String encPassword = encoder.encode(rawPassword); //해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
			
	}
	/*
	@Transactional(readOnly = true) //Select할 때 트랜잭션 시작,서비스 종료 트랜잭션 종료(정합성)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	*/
	
}
