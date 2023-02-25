package com.signify.collection;

import com.signify.bean.User;

public class UserSession {
	public static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User usr) {
		user = usr;
	}

	public static void clearSession() {
		user = null;
	}
}
