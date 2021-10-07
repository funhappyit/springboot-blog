package org.zerock.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.ResponseDto;
import org.zerock.model.RoleType;
import org.zerock.model.User;
import org.zerock.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		
		//실제로 DB에 insert를 하고 아래에서 insert하면 됌
		user.setRole(RoleType.USER);
		int result = userService.save(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result);
	}
}
