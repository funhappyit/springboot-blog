package org.zerock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.model.Board;
import org.zerock.model.User;

//DAO
//자동으로 bean등록이 된다. @Respository 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{


}
