package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bitacademy.mysite.vo.UserVo;

public class UserDao {
	

	public boolean update(UserVo userVo, String name) {
		// TODO Auto-generated method stub
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update user " + 
						"set name = ?, email = ?, password = ?, gender = ?" + 
						"where email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userVo.getName());
			pstmt.setString(2, userVo.getEmail());
			pstmt.setString(3, userVo.getPassword());
			pstmt.setString(4, userVo.getGender());
			pstmt.setString(5, userVo.getEmail());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		}catch (SQLException e) {
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
	
	public UserVo findByNo(Long no) {
		UserVo findUser = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select name, email, gender from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setFloat(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String gender = rs.getString(3);
				
				findUser = new UserVo();
				findUser.setName(name);
				findUser.setEmail(email);
				findUser.setGender(gender);
			}
			
			
		}catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if(rs != null) {
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
		
		return findUser;
	}
	
	
	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name from user where email = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				userVo.setNo(no);
				userVo.setName(name);
			}
			
			
		}catch (SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("error: " + e);
		} finally {
			try {
				// 자원 정리
				if(rs != null) {
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
		
		
		return userVo;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user" + 
					" values(null, ?, ?, ?, ?, now());";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		}catch (SQLException e) {
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
	
	private Connection getConnection() throws SQLException{
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
