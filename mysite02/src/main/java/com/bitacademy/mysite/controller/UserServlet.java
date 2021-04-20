package com.bitacademy.mysite.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.UserDao;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.WebUtil;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("a");
		
		if("joinform".equals(action)) {
			WebUtil.forword("/WEB-INF/views/user/joinform.jsp", request, response);
		}else if("join".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setPassword(password);
			userVo.setEmail(email);
			userVo.setGender(gender);
	
			new UserDao().insert(userVo);
			
			WebUtil.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
			
		}
		else if("joinsuccess".equals(action)) {
			WebUtil.forword("/WEB-INF/views/user/joinsuccess.jsp", request, response);
		}else if("loginform".equals(action)) {
			WebUtil.forword("/WEB-INF/views/user/loginform.jsp", request, response);
		}else if("login".equals(action)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserVo vo = new UserVo();
			vo.setEmail(email);
			vo.setPassword(password);
			
			UserVo authUser = new UserDao().findByEmailAndPassword(vo);
			
			//로그인 인증 실패한 경우
			if(authUser == null) {
				request.setAttribute("authResult", "fail");
				WebUtil.forword("/WEB-INF/views/user/loginform.jsp", request, response);
				return;
			}
			
			//로그인 인증 처리
			HttpSession session = request.getSession(true); //톰켓의 Session Manager에게 sessionid에 맵핑된 세션객체 return
			                                  //매개변수 true: 없으면 새로 만들어줘!, false: 없으면 null로 반환해줘!
			session.setAttribute("authUser", authUser); //authUser 이름으로 authUser 객체를 담는다.
			
			//응답
			WebUtil.redirect(request.getContextPath(), request, response);
			
		}else if("logout".equals(action)) {
			//1. 세션을 가져온다.
			HttpSession session = request.getSession();
			if(session == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
				return;
			}
			//2. authUser 이름의 객체를 찾아본다.
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
				return;
			}
			//3. 세션 안에 있는 authUser 객체를 삭제한다. ==> 로그아웃 처리
			if(session != null && session.getAttribute("authUser") != null) {
				session.removeAttribute("authUser");
				session.invalidate();
			}
			
			WebUtil.redirect(request.getContextPath(), request, response);
		} else if("updateform".equals(action)) {
			// Access Control (접근제어를 해야한다. - 로그인 한 경우에만)
			//1. 세션 객체 가져오기
			HttpSession session = request.getSession();
			if(session == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
			}
			
			//1. 세션에서 authUser 객체 가져오기
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request.getContextPath(), request, response);
				return;
			}
			
			Long no = authUser.getNo();
			UserVo userVo = new UserDao().findByNo(no);
			
			request.setAttribute("userVo", userVo);
			WebUtil.forword("/WEB-INF/views/user/updateform.jsp", request, response);
		}
		else if("update".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setPassword(password);
			userVo.setEmail(email);
			userVo.setGender(gender);
			
			new UserDao().update(userVo, email);
			
			WebUtil.redirect(request.getContextPath(), request, response);
			
		}
		else {
			WebUtil.redirect(request.getContextPath(), request, response);
		}
			
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
