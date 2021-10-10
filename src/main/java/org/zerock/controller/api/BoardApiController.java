package org.zerock.controller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.config.auth.PrincipalDetail;
import org.zerock.dto.ResponseDto;
import org.zerock.model.Board;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		
		boardService.write(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
