package com.signify.client;

import java.util.Scanner;

import com.signify.service.UserService;

public class CRSApplicationMenu {
	private static String prompt = "\n======================\n"
			+ "MAIN MENU\n"
			+ "PRESS 1 TO LOGIN\n"
			+ "PRESS 2 TO REGISTRATION\n"
			+ "PRESS 3 FOR UPDATE PASSWORD\n"
			+ "PRESS 4 EXIT\n";
	
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
				UserService adminService = new UserService();
				adminService.login(username , password);
				CRSAdminMenu adminMenu = new CRSAdminMenu();
				adminMenu.displayAdminMenu();
			}
			else if(role.equals("student")) {
				UserService studentService = new UserService();
				studentService.login(username , password);
				CRSStudentMenu studentMenu = new CRSStudentMenu();
				studentMenu.displayStudentMenu();
			}
			else if(role.equals("professor")) {
				UserService professorService = new UserService();
				professorService.login(username , password);
				CRSProfessorMenu profMenu = new CRSProfessorMenu();
				profMenu.displayProfessorMenu();
			}
			else {
				System.out.println("ERROR : Role can only be student , professor or admin");
			}
		}
	}
	public void registerStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERNAME");
		String username = sc.nextLine();
		System.out.println("ENTER PASSWORD");
		String password = sc.nextLine();
		
		UserService svc = new UserService();
		svc.register(username, password);
	}
	public void updatePassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERNAME ");
		String username = sc.nextLine();
		System.out.println("ENTER NEW PASSWORD ");
		String password = sc.nextLine();
		UserService svc = new UserService();
		svc.updatePassword(username , password);
	}
	public static void main(String[] args) {
	
		System.out.println("WELCOME TO THE CRS APPLICATION");
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int type;
		CRSApplicationMenu crs = new CRSApplicationMenu();
		do {
			System.out.println(prompt);
			type = sc.nextInt();
			
			switch(type) {
				case 1:
					// handle login
					crs.loginHandler();
					break;
				case 2:
					crs.registerStudent();
					break;
				case 3:
					crs.updatePassword();
					break;
				case 4:
					flag = false;
					System.out.println("BYE");
					return;
				default:
					System.out.println("WRONG INPUT : TRY AGAIN");
					break;
			}
		} while (flag);
	}
}
