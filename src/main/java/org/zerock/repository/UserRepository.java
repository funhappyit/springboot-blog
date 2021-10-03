package org.zerock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.model.User;

//DAO
//자동으로 bean등록이 된다. @Respository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{

	
	
}
