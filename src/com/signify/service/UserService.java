package com.signify.service;


public class UserService implements UserInterface {
	@Override
	public void login(String username , String password) {
		System.out.println("\nUser Logged In");
	}
	@Override
	public void logout() {
		System.out.println("\nUser Logged Out");
	}
	@Override
	public void register(String username , String password) {
		System.out.println("User Registered");
	}
	@Override
	public void updateDetails() {
		System.out.println("Details Updated");
	}
	@Override
	public void updatePassword(String username , String password) {
		System.out.println("Password updated");
	}
}
