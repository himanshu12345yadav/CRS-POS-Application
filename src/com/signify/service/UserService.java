package com.signify.service;

import com.signify.bean.User;
import com.signify.collection.UserData;

public class UserService implements UserInterface {
	@Override
	public boolean login(String username , String password , String role) {
		if(UserData.users.containsKey(username) && UserData.users.get(username).getPassword().equals(password) && UserData.users.get(username).getRole().equals(role)) {
			System.out.println("\n✓ User Logged In");
			return true;
		}
		else {
			System.out.println("\n⚠ Invalid Username , password or role");
			return false;
		}
	}
	@Override
	public void logout() {
		System.out.println("\n✓ User Logged Out\n_________________________");
	}
	@Override
	public void register(String username , String password , String name , String address , String role) { 
		if(UserData.users.containsKey(username)) {
			System.out.println("\n⚠User Already Registered , Try Login !");
		}
		else {
			User user = new User();
			user.setName(name);
			user.setAddress(address);
			user.setRole(role);
			user.setUsername(username);
			user.setPassword(password);
			user.setUserId(user.hashCode());
			UserData.users.put(username , user);
			System.out.println("\n✓ User Registered , Login Now !");
		}
	}
	@Override
	public void updatePassword(String username , String password ,  String newPassword) {
		if(UserData.users.containsKey(username) && UserData.users.get(username).getPassword().equals(password)) {
			UserData.users.get(username).setPassword(newPassword);
			System.out.println("\n✓ Password Updated Successfully");
		}
		else {
			System.out.println("\n⚠Invalid Username or password");
		}
	}
}
