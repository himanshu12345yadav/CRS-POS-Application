package com.signify.service;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.collection.UserSession;
import com.signify.collection.UserData;

public class UserService implements UserInterface {
	@Override
	public boolean login(String username , String password , String role) {
		if(UserData.users.containsKey(username) && UserData.users.get(username).getPassword().equals(password) && UserData.users.get(username).getRole().equals(role)) {
			if(role.equals("student")) {
				Student usr = (Student)UserData.users.get(username);
				if(usr.isApproved()) {
					System.out.println("\n✓ User Logged In");
					UserSession.user = UserData.users.get(username);
					return true;
				}
				else {
					System.out.println("\n⚠ Admin didn't approved yet");
					return false;
				}
			}
			else {
				System.out.println("\n✓ User Logged In");
				UserSession.user = UserData.users.get(username);
				return true;				
			}
		}
		else {
			System.out.println("\n⚠ Invalid Username , password or role");
			return false;
		}
	}
	@Override
	public void logout() {
		UserSession.user = null;
		System.out.println("\n✓ User Logged Out\n_________________________");
	}
	@Override
	public void register(String username , String password , String name , String address , String role) { 
		if(UserData.users.containsKey(username)) {
			System.out.println("\n⚠User Already Registered , Try Login !");
		}
		else {
			if(role.equals("student")) {
				Student student = new Student();
				student.setName(name);
				student.setAddress(address);
				student.setRole(role);
				student.setUsername(username);
				student.setPassword(password);
				student.setApproved(false);
				student.setUserId(student.hashCode());
				UserData.users.put(username , student);
			}
			else if(role.equals("professor")) {
				Professor prof = new Professor();
				prof.setName(name);
				prof.setAddress(address);
				prof.setRole(role);
				prof.setUsername(username);
				prof.setPassword(password);
				prof.setUserId(prof.hashCode());
				UserData.users.put(username , prof);
			}
			else if(role.equals("admin")) {
				Admin admin = new Admin();
				admin.setName(name);
				admin.setAddress(address);
				admin.setRole(role);
				admin.setUsername(username);
				admin.setPassword(password);
				admin.setUserId(admin.hashCode());
				UserData.users.put(username , admin);
			}
			
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
