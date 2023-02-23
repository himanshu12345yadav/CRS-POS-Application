package com.signify.service;

import com.signify.bean.User;

public interface UserInterface {
	public User login(String username , String password);
	public void logout();
	public void register(User usr);
	public void updatePassword(String username , String password , String newPassword);
}
