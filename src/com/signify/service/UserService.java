package com.signify.service;

import com.signify.bean.User;
import com.signify.collection.UserSession;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.UserDAO;

public class UserService implements UserInterface {
	@Override
	public User login(String username, String password) {
		UserDAO ud = new UserDAO();
		User usr = null;
		try {
			usr = ud.login(username, password);
			UserSession.user = usr;
		} catch (UserNotFoundException e) {
			System.err.printf("[error] : %s", e.getMessage());
		}

		return usr;
	}

	@Override
	public void logout() {
		UserSession.user = null;
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
