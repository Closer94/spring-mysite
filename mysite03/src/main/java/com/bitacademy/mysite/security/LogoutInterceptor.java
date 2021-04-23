package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {

		HttpSession session = request.getSession();
		
		// 로그아웃 처리
		if(session != null) {
			session.removeAttribute("authUser"); //세션 지우고
			session.invalidate(); //세션 비활성화(세션 삭제)
		}
		
		response.sendRedirect(request.getContextPath());
		return false;
	}
}
