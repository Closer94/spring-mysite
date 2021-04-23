package com.bitacademy.mysite.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bitacademy.dto.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice // 컨트롤러의 Exception 이기때문에 ControllerAdvice 어노테이션 붙여준다.
public class GlobalExceptionHandler {
	// 모든 예외가 모여서 처리하는 메서드
	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		// 1. 로그 작업
		System.out.println(e);

		// 2. 요청구분 (api 요청에 대한 오류인지, 사용자 요청에 대한 오류인지)
		String accept = request.getHeader("accept");
		
		//2-1. json을 이용한 api요청
		if(accept.matches(".*application/json.*")) {
			response.setStatus(HttpServletResponse.SC_OK); //에러지만 통신은 정상적이기 때문에 SC_OK 로 설정
			
			JsonResult jsonResult = JsonResult.fail(e.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
		}
		// 2-2. 사과문 페이지로 전달(컨트롤러가 아니기때문에 직접 request를 받아서 dispatcher에 전달해야한다.)
		else {
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		
	
	}
}
