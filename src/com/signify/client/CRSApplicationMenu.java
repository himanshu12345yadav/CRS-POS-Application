package com.signify.client;

import java.util.Scanner;

import com.signify.bean.User;
import com.signify.collection.UserData;
import com.signify.service.UserService;

public class CRSApplicationMenu {
	private static String prompt = "\nMAIN MENU\n---------\n"
			+ "PRESS 1 TO LOGIN\n"
			+ "PRESS 2 TO REGISTRATION\n"
			+ "PRESS 3 TO UPDATE PASSWORD\n"
			+ "PRESS 4 TO EXIT\n";
	
	public void loginHandler() {
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("ENTER USERNAME :");
			String username = sc.nextLine();
			System.out.print("ENTER PASSWORD :");
			String password = sc.nextLine();
			System.out.print("ENTER ROLE :");
			String role = sc.nextLine().toLowerCase();
			
			if(role.equals("admin")) {
				UserService adminService = new UserService();
				if(adminService.login(username , password , role)) {
					CRSAdminMenu adminMenu = new CRSAdminMenu();
					adminMenu.displayAdminMenu();					
				}
			}
			else if(role.equals("student")) {
				UserService studentService = new UserService();
				if(studentService.login(username , password , role)) {
					CRSStudentMenu studentMenu = new CRSStudentMenu();
					studentMenu.displayStudentMenu();					
				}
			}
			else if(role.equals("professor")) {
				UserService professorService = new UserService();
				if(professorService.login(username , password , role)) {
					CRSProfessorMenu profMenu = new CRSProfessorMenu();
					profMenu.displayProfessorMenu();					
				}
			}
			else {
				System.out.println("ERROR TYPE ROLE : Role can only be student , professor or admin");
			}
		}
	}
	public void registerHandler() {
		// allow registration for student
		Scanner sc = new Scanner(System.in);
		System.out.print("ENTER USERNAME :");
		String username = sc.nextLine();
		System.out.print("ENTER PASSWORD :");
		String password = sc.nextLine();
		System.out.print("ENTER NAME :");
		String name = sc.nextLine();
		System.out.print("ENTER ADDRESS :");
		String address = sc.nextLine();
		System.out.print("ENTER ROLE :");
		String role = sc.nextLine().toLowerCase();
		
		if(!role.equals("student")) {
			System.out.println("\n⚠ Only Student can Register");
			return;
		}
		
		UserService svc = new UserService();
		svc.register(username, password , name , address , role);
	}
	public void updatePassword() {
		Scanner sc = new Scanner(System.in);
		System.out.print("ENTER USERNAME :");
		String username = sc.nextLine();
		System.out.print("ENTER CURRENT PASSWORD :");
		String password = sc.nextLine();
		System.out.print("ENTER NEW PASSWORD :");
		String newPassword = sc.nextLine();
		UserService svc = new UserService();
		svc.updatePassword(username , password , newPassword);
	}
	public static void main(String[] args) {
	
		System.out.println("WELCOME TO THE CRS APPLICATION\n==============================");
		createAdmin();
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		CRSApplicationMenu crs = new CRSApplicationMenu();
		do {
			System.out.println(prompt);
			String type = sc.next();
			
			switch(type) {
				case "1":
					crs.loginHandler();
					break;
				case "2":
					crs.registerHandler();
					break;
				case "3":
					crs.updatePassword();
					break;
				case "4":
					flag = false;
					System.out.println("👋 BYE");
					return;
				default:
					System.out.println("WRONG INPUT : TRY AGAIN");
					break;
			}
		} while (flag);
	}
	private static void createAdmin() {
		User admin = new User();
		admin.setName("admin");
		admin.setAddress("admin");
		admin.setPassword("admin");
		admin.setUserId(admin.hashCode());
		admin.setUsername("admin");
		admin.setRole("admin");
		UserData.users.put(admin.getUsername(), admin);
	}
}
