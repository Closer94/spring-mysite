package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public boolean delete(GuestbookVo vo) {
		boolean result = false;

		int count = sqlSession.delete("guestbook.delete", vo);
		result = count == 1;
	/*
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			// 3. sql문 준비
			String sql = "delete from guestbook where no = ? and password = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getNo().toString());
			pstmt.setString(2, vo.getPassword());

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
	*/
		return result;
	}

	public boolean insert(GuestbookVo vo) {
		boolean result = false; 
		int count = sqlSession.insert("guestbook.insert", vo);
		
		result = count == 1;		
		
	/*
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			// 3. sql문 준비
			String sql = "insert into guestbook " + "values(null, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());

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
	*/
		return result;
	}

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		
		/*
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			// 3. sql문 준비
			String sql = "select no, name, contents, date_format(reg_date, '%Y-%m-%d %H:%i:%s') from guestbook"
					+ " order by reg_date desc";
			pstmt = conn.prepareStatement(sql);

			// 4. 실행
			rs = pstmt.executeQuery();

			// 5. 실행한 쿼리문에 대한 결과 데이터 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String reg_date = rs.getString(4);

				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContents(contents);
				vo.setReg_date(reg_date);

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
		*/
		
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
