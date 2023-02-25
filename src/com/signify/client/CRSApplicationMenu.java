package com.signify.client;

import java.util.Scanner;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.reader.UserRegistration;
import com.signify.service.UserService;

public class CRSApplicationMenu {
	private static String prompt = "\nMAIN MENU\n---------\n" + "1. LOGIN\n" + "2. REGISTER\n" + "3. UPDATE PASSWORD\n"
			+ "4. EXIT\n";

	public void loginHandler() {
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("ENTER USERNAME :");
			String username = sc.nextLine();
			System.out.print("ENTER PASSWORD :");
			String password = sc.nextLine();

			UserService svc = new UserService();

			User usr = svc.login(username, password);

			if (usr != null) {
				if (usr instanceof Student) {
					CRSStudentMenu stMenu = new CRSStudentMenu();
				} else if (usr instanceof Admin) {
					CRSAdminMenu adminMenu = new CRSAdminMenu();
					adminMenu.displayAdminMenu();
				} else if (usr instanceof Professor) {
					CRSProfessorMenu profMenu = new CRSProfessorMenu();
					profMenu.displayProfessorMenu();
				}
			} else {
				return;
			}
		}
	}

	public void registerHandler() {
		UserRegistration userReg = new UserRegistration();
		User usr = userReg.registerStudent();
		UserService svc = new UserService();
		svc.register(usr);
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
		svc.updatePassword(username, password, newPassword);
	}

	public static void main(String[] args) {

		System.out.println("WELCOME TO THE CRS APPLICATION\n==============================");
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		CRSApplicationMenu crs = new CRSApplicationMenu();
		do {
			System.out.println(prompt);
			String type = sc.next();

			switch (type) {
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
}
