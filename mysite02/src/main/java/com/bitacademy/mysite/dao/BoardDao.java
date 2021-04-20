package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.GuestbookVo;

public class BoardDao {
	
	public boolean updateBoard(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "update board " + 
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
	
	public boolean updateViewCnt(int viewCnt, String regDate) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "update board " + 
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
	
	public boolean delete(String regDate) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "delete from board where regdate = ?";
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
	
	public BoardVo findTitleContent(String title_, String content_) {
		BoardVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select no, title, content, writerId, viewCnt, date_format(regDate, '%Y-%m-%d %H:%i:%s') from board"
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

				vo = new BoardVo();
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
	
	public BoardVo find(String title, String regDate) {
		BoardVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select no, title, content, writerId, viewCnt, date_format(regDate, '%Y-%m-%d %H:%i:%s') from board"
					+ "  where title=? and regdate=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, regDate);
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

				vo = new BoardVo();
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
	
	public boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "insert into board(title, content, writerId,viewCnt, regdate) " + 
					"values(?, ?, ?, 0, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriterId());

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
	
	public List<BoardVo> findAll(int page) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		int start = 1 + (page-1) * 10; //1, 11, 21, 31
		int end = page * 10; //10, 20, 30, 40

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select N2.* from " + 
					"	( select @ROWNUM:=@ROWNUM+1 num, N1.* " + 
					"	from ( " + 
					"		select * " + 
					"		from board " + 
					"		order by regdate desc " + 
					"	) as N1 " + 
					"    WHERE (@rownum:=0)=0 " + 
					") as N2 " + 
					"where num between ? and ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writerId = rs.getString("writerId");
				int viewCnt = rs.getInt("viewCnt");
				String regDate = rs.getString("regdate");

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriterId(writerId);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(regDate);
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
	
	public List<BoardVo> findAll_origin(int page) {
		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. sql문 준비
			String sql = "select no, title, content, writerId, viewCnt, date_format(regDate, '%Y-%m-%d %H:%i:%s') from board"
					+ " order by regDate desc";
			pstmt = conn.prepareStatement(sql);

			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writerId = rs.getString(4);
				int viewCnt = rs.getInt(5);
				String regDate = rs.getString(6);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriterId(writerId);
				vo.setViewCnt(viewCnt);
				vo.setRegDate(regDate);
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
