package org.zerock.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() { //컨트롤러에서 세션을 어떻게 찾냐?
		
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	
}
