package com.signify.client;

import java.util.Scanner;
import com.signify.service.AdminService;

public class CRSAdminMenu {
	private String prompt = "ADMIN MENU\n------------\nPRESS 1 -> FOR APPROVING STUDENT \n"
			+ "PRESS 2 -> TO ADD PROFESSOR \n"
			+ "PRESS 3 -> TO GENERATE REPORT CARD \n"
			+ "PRESS 4 -> TO ASSIGN A PROFESSOR\n"
			+ "PRESS 5 -> TO ADD COURSE \n"
			+ "PRESS 6 -> TO REMOVE COURSE\n"
			+ "PRESS 7 -> TO VIEW COURSES\n"
			+ "PRESS 8 -> GO TO MAIN MENU\n";
	
	public void displayAdminMenu() {
		System.out.println(prompt);
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		AdminService as = new AdminService();
		
		do {
			int input = sc.nextInt();
			
			switch(input) {
			case 1 :
				as.approveStudent(1);
				break;
			case 2 :
				as.addProfessor(10);
				break;
			case 3 :
				as.generateReportCard(1, 1, 1);
				break;
			case 4 :
				as.assignProfessorToCourse(1, 10);
				break;
			case 5 :
				as.addCourse(10);
				break;
			case 6 :
				as.removeCourse(10);
				break;
			case 7 : 
				as.viewCourseDetails(10);
				break;
			case 8:
				return;
			default:
				System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}
	
}
