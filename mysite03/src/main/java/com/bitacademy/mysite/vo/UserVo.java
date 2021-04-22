package com.bitacademy.mysite.vo;

public class UserVo {
	private Long no;
	private String name;
	private String email;
	private String password;
	private String gender;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getNo() {
		return no;
	}
	@Override
	public String toString() {
		return "UserVo [name=" + name + ", email=" + email + ", password=" + password + ", gender=" + gender + "]";
	}
	
	
}
