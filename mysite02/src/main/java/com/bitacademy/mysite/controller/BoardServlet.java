package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.dao.ReplyBoardDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.ReplyBoardVo;
import com.bitacademy.mysite.vo.UserVo;
import com.bitacademy.web.mvc.WebUtil;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) {
			WebUtil.forword("/WEB-INF/views/board/write.jsp", request, response);
		}
		else if("write".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
			UserVo authUser = getAuthUser(request, response);
			System.out.println(authUser);
			
			String writerId = authUser.getName();
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterId(writerId);
			
			new BoardDao().insert(vo);

			WebUtil.redirect(request.getContextPath()+"/board", request, response);
		}
		else if("view".equals(action)) {
			
			String title = request.getParameter("title");
			String regDate = request.getParameter("regdate");
			
			BoardVo vo = new BoardDao().find(title, regDate);
			
			//조회수 가져오기, 조회수 증가후 갱신하기
			int viewCnt = vo.getViewCnt();
			new BoardDao().updateViewCnt(viewCnt, regDate);
			
			System.out.println("viewCnt : " + viewCnt);
			vo.setViewCnt(viewCnt);
			
			request.setAttribute("vo", vo);
			
			WebUtil.forword("/WEB-INF/views/board/view.jsp", request, response);
		}
		else if("modify".equals(action)) {
			String title = request.getParameter("title");
			String regDate = request.getParameter("regdate");
			
			BoardVo vo = new BoardDao().find(title, regDate);
			
			request.setAttribute("vo", vo);
			WebUtil.forword("/WEB-INF/views/board/modify.jsp", request, response);
		} 
		else if("modifyConfirm".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String originTitle = request.getParameter("originTitle");
			String originContent = request.getParameter("originContent");
			
			BoardVo vo = new BoardDao().findTitleContent(originTitle, originContent);
			
			vo.setTitle(title);
			vo.setContent(content);
			
			new BoardDao().updateBoard(vo);
			
			request.setAttribute("vo", vo);
			
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		}
		else if("deleteform".equals(action)) {
			String regDate = request.getParameter("regDate");
			
			request.setAttribute("regDate", regDate);
			
			WebUtil.forword("/WEB-INF/views/board/deleteform.jsp", request, response);
		}
		else if("delete".equals(action)) {
			String regDate = request.getParameter("regdate");
			System.out.println("regDate: " + regDate);
			
			new BoardDao().delete(regDate);
			
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		}
		else if("replyform".equals(action)) {
			String title = request.getParameter("title");
			String regDate = request.getParameter("regdate");
			
			BoardVo rootBoardVo = new BoardDao().find(title, regDate);
			
			request.setAttribute("rootBoardVo", rootBoardVo);
			
			WebUtil.forword("/WEB-INF/views/board/writeReply.jsp", request, response);
		}
		else if("reply".equals(action)) {
			//댓글 단 게시물의 no
			String rootBoardNo = request.getParameter("rootBoardNo");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
		
			UserVo authUser = getAuthUser(request, response);
			
			System.out.println(authUser);
			
			String writerId = authUser.getName();
			
			ReplyBoardVo vo = new ReplyBoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterId(writerId);
			vo.setGroup_no(Integer.parseInt(rootBoardNo));
			
			new ReplyBoardDao().insertReply(vo);
			
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		}else if("deleteReplyform".equals(action)){
			String regDate = request.getParameter("regDate");
			
			request.setAttribute("regDate", regDate);
			
			WebUtil.forword("/WEB-INF/views/board/replydeleteform.jsp", request, response);
		} else if("deleteReply".equals(action)) {
			String regDate = request.getParameter("regdate");
			
			new ReplyBoardDao().delete(regDate);
			
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		} 
		else if("viewReply".equals(action)) {
			String title = request.getParameter("title");
			String regDate = request.getParameter("regdate");
			
			ReplyBoardVo vo = new ReplyBoardDao().find(title, regDate);
			
			//조회수 가져오기, 조회수 증가후 갱신하기
			int viewCnt = vo.getViewCnt();
			new ReplyBoardDao().updateViewCnt(viewCnt, regDate);
			
			vo.setViewCnt(viewCnt);
			
			request.setAttribute("vo", vo);
			
			WebUtil.forword("/WEB-INF/views/board/viewReply.jsp", request, response);
			
			
		}
		else if("modifyReply".equals(action)) {
			String title = request.getParameter("title");
			String regDate = request.getParameter("regdate");
			
			ReplyBoardVo vo = new ReplyBoardDao().find(title, regDate);
			
			request.setAttribute("vo", vo);
			WebUtil.forword("/WEB-INF/views/board/modifyReply.jsp", request, response);
		}
		else if("modifyReplyConfirm".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String originTitle = request.getParameter("originTitle");
			String originContent = request.getParameter("originContent");
			
			ReplyBoardVo vo = new ReplyBoardDao().findTitleContent(originTitle, originContent);
			
			vo.setTitle(title);
			vo.setContent(content);
			
			new ReplyBoardDao().updateBoard(vo);
			
			request.setAttribute("vo", vo);
			
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
		}
		else {
			String page_ = request.getParameter("p");
			int page = 1;
			if (page_ != null && !page_.equals(""))
				page = Integer.parseInt(page_);
		
			List<BoardVo> list = new BoardDao().findAll(page);
			List<ReplyBoardVo> replyList = new ReplyBoardDao().replyfindAll();
			
			int count = list.size(); //게시판 총 개수
			 
			request.setAttribute("count", count);
			request.setAttribute("list", list);
			request.setAttribute("replyList", replyList);
			
			WebUtil.forword("/WEB-INF/views/board/index.jsp", request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public static UserVo getAuthUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓴이의 id값 가져오기
		//1. 세션 객체 가져오기
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.forword("/WEB-INF/views/board/index.jsp", request, response);
			return null;
		}
		//2. 세션 객체에서 authUser 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.forword("/WEB-INF/views/board/index.jsp", request, response);
			return null;
		}
		
		return authUser;
	}
}
