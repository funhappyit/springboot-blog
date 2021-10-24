package org.zerock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
}
