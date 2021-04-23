package com.bitacademy.dto;

public class JsonResult {
	private String result; //success or fail
	private Object data; // if result = success data else null
	private String message; // if result = success null else error message
	
	private JsonResult() {
		
	}
	
	//에러일때 생성자
	private JsonResult(String message) {
		this.result = "fail";
		this.message = message;
	}
	
	//성공일때 생성자
	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
		
	}
	
	
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
	
	
	
}
