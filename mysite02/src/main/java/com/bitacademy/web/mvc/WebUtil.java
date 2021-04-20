package com.bitacademy.web.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

	public static void forword(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path); //어디로 보낼지 기록
		rd.forward(request, response);
//		request.getRequestDispatcher(path).forward(request, response); 한번에 세팅 가능
	}
	
	public static void redirect(String url,  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(url);
	}
	
}
