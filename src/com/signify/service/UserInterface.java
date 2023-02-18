package com.signify.service;

public interface UserInterface {
	public void login(String username , String password);
	public void logout();
	public void register(String username , String password);
	public void updateDetails();
	public void updatePassword(String username , String password);
}
