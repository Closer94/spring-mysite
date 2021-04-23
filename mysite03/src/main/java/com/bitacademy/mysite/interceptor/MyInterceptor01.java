package com.bitacademy.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터페이스로 Interceptor 만든경우
public class MyInterceptor01 implements HandlerInterceptor {

	//Handler 처리하기 전에 (시스템 외부)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("MyInterceptor01.preHandle");
		return false; // return false; 로 하면 preHandle까지만 들어고 막는다.  || return true; 인 경우 통과시킨다.
	}

	//Handler 끝난 후에
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("MyInterceptor01.postHandle");
	}

	//ViewResolver에 fowarding이 끝난 경우
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println("MyInterceptor01.afterCompletion");
		
	}

}
