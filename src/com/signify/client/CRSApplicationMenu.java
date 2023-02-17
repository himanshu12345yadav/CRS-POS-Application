package com.signify.client;

import java.util.Scanner;

import com.signify.service.AdminService;
import com.signify.service.ProfessorService;
import com.signify.service.StudentService;

public class CRSApplicationMenu {
	
	public void loginHandler() {
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("ENTER USERNAME");
			String username = sc.nextLine();
			System.out.println("ENTER PASSWORD");
			String password = sc.nextLine();
			System.out.println("ENTER ROLE");
			String role = sc.nextLine();
			
			if(role.equals("admin")) {
				System.out.println("WELCOME TO ADMIN");
				AdminService adminService = new AdminService();
				adminService.register();
			}
			else if(role.equals("student")) {
				System.out.println("WELCOME TO STUDENT");
				StudentService studentService = new StudentService();
				studentService.register();
			}
			else if(role.equals("professor")) {
				System.out.println("WELCOME TO PROFESSOR");
				ProfessorService professorService = new ProfessorService();
				professorService.register();
			}
			else {
				System.out.println("TRY AGAIN!");
			}
		}
	}
	public void registerStudent() {
		
	}
	public void updatePassword() {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("WELCOME TO THE CRS APPLICATION");
		System.out.println("======================");
		System.out.println("\nMAIN MENU");
		System.out.println("PRESS 1 TO LOGIN");
		System.out.println("PRESS 2 TO REGISTRATION");
		System.out.println("PRESS 3 FOR UPDATE PASSWORD");
		System.out.println("PRESS 4 EXIT");
		
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int type;
		do {
			type = sc.nextInt();
			
			switch(type) {
				case 1:
					// handle login
					CRSApplicationMenu crs = new CRSApplicationMenu();
					crs.loginHandler();
					break;
				case 2:
					System.out.println("Registration");
					break;
				case 3:
					System.out.println("Update password");
					break;
				case 4:
					flag = false;
					System.out.println("EXIT");
					break;
				default:
					System.out.println("WRONG INPUT : TRY AGAIN");
					break;
			}
		} while (flag);
	}
}
