package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. Handler 종류 확인해 본다.
		// DeafultServletHandler가 처리하는 경우(보통, assets의 정정 자원 접근)
		if(handler instanceof HandlerMethod == false) {
			return true; //통과시켜준다.
		}
		
		//2. Handler Method 인 경우 casting 해준다.
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Method에 @Auth 달려 있는지 확인하기 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Method 에 @Auth가 안달려 있으면 return true; (패스시켜준다.)
		if(auth == null) {
			return true;
		}
		
		//5. @Auth가 달려 있는 경우에는 인증(Authetication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) { // 세션이 없는 경우 돌려보낸다.(로그인 하는 페이지로)
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//6. 세션이 있는 경우에는
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { // 인증이 없는 경우 돌려보낸다.(로그인 하는 페이지로)
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;			
		}
		
		return true;
	}

}