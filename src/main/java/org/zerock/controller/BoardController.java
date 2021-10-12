package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.config.auth.PrincipalDetail;
import org.zerock.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model) { //컨트롤러에서 세션을 어떻게 찾냐?
		model.addAttribute("boards", boardService.boardList());
		return "index"; // viewResolver 작동!!
	}
	
	//USER 권한이 필요 
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	
}
