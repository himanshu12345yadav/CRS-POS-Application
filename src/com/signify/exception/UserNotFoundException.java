package com.signify.exception;

public class UserNotFoundException extends Exception {
	private String username;
	
	public UserNotFoundException(String username) {
		this.username = username;
	}
	
	@Override
	public String getMessage() {
		return "Invalid Username or Password";
	}
}
