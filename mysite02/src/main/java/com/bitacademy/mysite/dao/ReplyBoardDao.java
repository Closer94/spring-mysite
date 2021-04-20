package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.ReplyBoardVo;

public class ReplyBoardDao {

	public boolean updateBoard(ReplyBoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "update replyboard " + 
					"set title = ?, content = ? " + 
					"where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			// 4. 실행
			int count = pstmt.executeUpdate();

			// 5. 결과
			result = count == 1;

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public ReplyBoardVo findTitleContent(String title_, String content_) {
		ReplyBoardVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select no, title, content, writerId, viewCnt, date_format(regDate, '%Y-%m-%d %H:%i:%s') from replyboard"
					+ " where title=? and content=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title_);
			pstmt.setString(2, content_);
			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			if(rs.next()) { 
				int no = rs.getInt(1);
				String _title = rs.getString(2);
				String content = rs.getString(3);
				String writerId = rs.getString(4);
				int viewCnt = rs.getInt(5);
				String _regDate = rs.getString(6);

				vo = new ReplyBoardVo();
				vo.setNo(no);
				vo.setTitle(_title);
				vo.setContent(content);
				vo.setWriterId(writerId);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(_regDate);
				
			}	

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return vo;
	}
	
	public boolean updateViewCnt(int viewCnt, String regDate) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "update replyboard " + 
							"set viewCnt = ? + 1 " + 
							"where regdate = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, viewCnt);
			pstmt.setString(2, regDate);

			// 4. 실행
			int count = pstmt.executeUpdate();

			// 5. 결과
			result = count == 1;

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
		
	}
	
	public ReplyBoardVo find(String title_, String regDate_) {
		ReplyBoardVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select group_no, no, title, content, writerId, viewCnt, regdate, order_no " + 
					"from replyboard " + 
					"where title=? and regdate=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title_);
			pstmt.setString(2, regDate_);
			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			if(rs.next()) { 
				int group_no = rs.getInt("group_no");
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writerId = rs.getString("writerId");
				int viewCnt = rs.getInt("viewCnt");
				String regDate = rs.getString("regdate");
				int order_no = rs.getInt("order_no");
				vo = new ReplyBoardVo();
				vo.setGroup_no(group_no);
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriterId(writerId);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(regDate);
				vo.setOrder_no(order_no);
				
			}	

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return vo;
	}

	public boolean delete(String regDate) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "delete from replyboard where regdate = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, regDate);

			// 4. 실행
			int count = pstmt.executeUpdate();

			// 5. 결과
			result = count == 1;

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
		
	}
	
	public List<ReplyBoardVo> replyfindAll() {
		List<ReplyBoardVo> list = new ArrayList<ReplyBoardVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select group_no, no, title, content, writerId, viewCnt, regdate, order_no " + 
					"from replyboard " + 
					"order by group_no;";
			pstmt = conn.prepareStatement(sql);

					
			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			while (rs.next()) {
				int group_no = rs.getInt("group_no");
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writerId = rs.getString("writerId");
				int viewCnt = rs.getInt("viewCnt");
				String regDate = rs.getString("regdate");
				int order_no = rs.getInt("order_no");
				
				ReplyBoardVo vo = new ReplyBoardVo();
				vo.setGroup_no(group_no);
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriterId(writerId);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(regDate);
				vo.setOrder_no(order_no);
				
				list.add(vo);
			}

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	public boolean insertReply(ReplyBoardVo vo){
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "insert into replyboard(title, content, writerId, viewCnt, regdate, group_no, order_no, depth) " + 
					"values(?, ?, ?, 0, now(), ?, (select ifnull(MAX(order_no), 0) from replyboard a where group_no=? order by order_no desc) +1, (select ifnull(MAX(depth), 0) from replyboard a where group_no=? order by depth desc) +1)";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriterId());
			pstmt.setInt(4, vo.getGroup_no());
			pstmt.setInt(5, vo.getGroup_no());
			pstmt.setInt(6, vo.getGroup_no());
			
			// 4. 실행
			int count = pstmt.executeUpdate();

			// 5. 결과
			result = count == 1;

		} catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		// 1. JDBC 드라이버 객체 생성, 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "root", "369369");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}


	

}
