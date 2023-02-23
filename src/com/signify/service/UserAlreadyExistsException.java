package com.signify.service;

public class UserAlreadyExistsException extends Exception {
	private String username;

	public UserAlreadyExistsException(String username) {
		this.username = username;
	}

	@Override
	public String getMessage() {
		return "Invalid Username or Password";
	}
}
