package com.signify.service;

import com.signify.bean.User;
import com.signify.exception.UserAlreadyExistsException;

public interface UserInterface {
	public User login(String username , String password);
	public void logout();
	public void register(User usr) throws UserAlreadyExistsException;
	public void updatePassword(String username , String password , String newPassword);
}
