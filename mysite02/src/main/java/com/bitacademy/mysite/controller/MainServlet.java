package com.bitacademy.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.web.mvc.WebUtil;
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//---------------- Filter ---------------------------------------
	@Override
	public void init() throws ServletException {
		System.out.println("init() called");
		super.init();
	}
	
	//---------------- Filter ---------------------------------------
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() called");
		super.service(req, resp);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() called");
		request.setCharacterEncoding("utf-8");
		
		int visitCount = 0;
		// 쿠키 읽기
		Cookie[] cookies = request.getCookies(); //쿠키는 request에 담아서 보낸다.
		
		// 쿠키가 있는경우. (만약 쿠키가 없다면 cookies == null)
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if("visitCount".equals(cookie.getName())) {
					visitCount = Integer.parseInt(cookie.getValue()); //cookie.getValue()의 리턴 타입은 String이다.
				}
			}
		}
		
		System.out.println("visitCount : " + visitCount);
		
		// 쿠키 쓰기
		visitCount++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(visitCount)); //Cookie에 값을 넣을때 String 타입으로 넣는다.
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24*60*60); //1day (하루있다가 쿠키 삭제)
		
		
		response.addCookie(cookie);
		
		WebUtil.forword("/WEB-INF/views/main/index.jsp", request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() called");
		doGet(request, response);
	}

	
	//---------------- Filter ---------------------------------------
	@Override
	public void destroy() {
		System.out.println("destory() called!!!!!!!!!!!!!!!!!!!!!!!!!!");
		super.destroy();
	}
}
