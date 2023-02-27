package com.signify.service;

import com.signify.bean.User;
import com.signify.collection.UserSession;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAO;

public class UserService implements UserInterface {
	@Override
	public User login(String username, String password) throws UserNotFoundException {
		UserDAO ud = new UserDAO();
		return ud.login(username, password);
	}

	@Override
	public void logout() {
		UserSession.clearSession();
		System.out.println("\n✓ User Logged Out\n_________________________");
	}

	@Override
	public void register(User usr) throws UserAlreadyExistsException {
		UserDAO ud = new UserDAO();
		ud.registerUser(usr);
	}

	@Override
	public void updatePassword(String username, String password, String newPassword) {
		UserDAO ud = new UserDAO();
		try {
			ud.updatePassword(username, password, newPassword);
			System.out.println("[info] : Password changed successfully");
		} catch (UserNotFoundException e) {
			System.err.printf("[error] : %s", e.getMessage());
		}
	}
}
