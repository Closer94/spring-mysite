package com.bitacademy.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bitacademy.mysite.vo.UserVo;

//@Auth 어노테이션을 검사해서 처리하는 Handler를 작성
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 1. Handler 종류를 확인해본다.
		// DefaultServletHandler가 처리하는 경우(보통, asset의 정적 자원 접근인 경우이다.)
		if(handler instanceof HandlerMethod == false) {
			return true; //통과시켜준다.
		}
		
		// 2. Handler Method 인 경우 casting 해준다.
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Method에 @Auth 달려 있는지 확인하기 
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		// 4. Method 에 @Auth가 안달려 있으면 return true; (패스시켜준다.)
		if(auth == null) {
			return true;
		}
		
		// 5. @Auth가 달려 있는 경우에는 인증(Authetication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) { // 세션이 없는 경우 돌려보낸다.(로그인 하는 페이지로)
			response.sendRedirect(request.getContextPath() + "/user/login");
			
			return false;
		}
		
		// 6. 인증이 있는 경우에는 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { // 인증이 없는 경우 돌려보낸다.(로그인 하는 페이지로)
			response.sendRedirect(request.getContextPath() + "/user/login");
			
			return false;
		}
		
		return false;
	}

}
