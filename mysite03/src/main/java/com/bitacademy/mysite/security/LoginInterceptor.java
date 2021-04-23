package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserVo userVo = new UserVo();
		userVo.setEmail(email);
		userVo.setPassword(password);
		

		UserVo authUser = userService.getUser(userVo);
		//로그인 인증 실패한경우
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login?result=fail");
			return false;
		}
		
		//로그인 인증 성공한 경우
		//session 처리
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
	
}
