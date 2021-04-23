package com.bitacademy.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitacademy.mysite.service.UserService;

@Controller("userApiController")
//@RestController("userApiController") //컨트롤러인데 API 전용 컨트롤러 라는 설정 => Controller(메서드에) @ResponseBody를 붙이지 않아도 된다.
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/existemail")
	public Map<String, Object> existEmail(String email){
		Boolean result = userService.existUser(email);
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		map.put("data", result);
				
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "안녕하세요";
	}
}
