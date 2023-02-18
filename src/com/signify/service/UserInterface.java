package com.signify.service;

public interface UserInterface {
	public boolean login(String username , String password , String role);
	public void logout();
	public void register(String username , String password , String name , String address , String role);
	public void updatePassword(String username , String password , String newPassword);
}
