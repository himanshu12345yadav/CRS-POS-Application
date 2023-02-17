package com.signify.service;

import java.util.Scanner;

public abstract class UserService implements UserInterface {
	public void register() {
		Scanner sc = new Scanner(System.in);
		
		String username = sc.next();
		String password = sc.next();
		String role = sc.next();
		
		if(role == "admin") {
			System.out.println("WELCOME TO ADMIN");
		}
		else if(role == "student") {
			System.out.println("WELCOME TO STUDENT");
		}
		else if(role == "professor") {
			System.out.println("WELCOME TO PROFESSOR");
		}
		else {
			System.out.println("TRY AGAIN!");
		}
		
		sc.close();
	}
	public void updateDetails() {
		
	}
	public void updatePassword() {
		
	}
}
