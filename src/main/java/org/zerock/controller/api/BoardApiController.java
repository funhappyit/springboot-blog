package org.zerock.controller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.config.auth.PrincipalDetail;
import org.zerock.dto.ReplySaveRequestDto;
import org.zerock.dto.ResponseDto;
import org.zerock.model.Board;
import org.zerock.model.Reply;
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
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.deleteBoard(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.updateBoard(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//데이터를 받을때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	//dto 사용하지 않은 이유는!!
	@PostMapping("/api/board/{boardId}/reply")
	//public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply,@AuthenticationPrincipal PrincipalDetail principal) {
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.commentWrite(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.commentDelete(replyId);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
}
