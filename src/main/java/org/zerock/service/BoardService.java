package org.zerock.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.model.Board;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.repository.BoardRepository;
import org.zerock.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌.Ioc를 해준다.
@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	@Transactional
	public void write(Board board,User user) {//title,content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public Page<Board> boardList(Pageable pageable){
		//findAll로 하면 다가져올 수 있다.
		return boardRepository.findAll(pageable);
	}
	/*
	@Transactional(readOnly = true) //Select할 때 트랜잭션 시작,서비스 종료 트랜잭션 종료(정합성)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	*/
	
}
