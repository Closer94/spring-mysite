package com.bitacademy.mysite.exception;

public class UserRepositoryException extends RuntimeException {

	private static final long serialVersionUID = -4249535937854802249L;

	public UserRepositoryException() {
		super("Throwing UserRepositoryException");
	}
	
	public UserRepositoryException(String message) {
		super(message);
	}
	
}
