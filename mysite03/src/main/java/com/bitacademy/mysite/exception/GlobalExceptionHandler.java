package com.bitacademy.mysite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 컨트롤러의 Exception 이기때문에 ControllerAdvice 어노테이션 붙여준다.
public class GlobalExceptionHandler {
	// 모든 예외가 모여서 처리하는 메서드
	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		// 1. 로그 작업
		System.out.println(e);

		// 사과문 페이지로 전달(컨트롤러가 아니기때문에 직접 request를 받아서 dispatcher에 전달해야한다.)
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
	}
}
