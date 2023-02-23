package com.signify.exception;

public class UnApprovedStudentNotFoundException extends Exception {
	public String getMessage(String str) {
		return "No Unapproved Student Found";
	}
}
