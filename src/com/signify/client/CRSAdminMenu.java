package com.signify.client;

import java.util.Scanner;
import com.signify.service.AdminService;

public class CRSAdminMenu {
	private String prompt = "PRESS 1 -> FOR APPROVING STUDENT \n"
			+ "PRESS 2 -> TO ADD PROFESSOR \n"
			+ "PRESS 3 -> TO GENERATE REPORT CARD \n"
			+ "PRESS 4 -> TO ASSIGN A PROFESSOR\n"
			+ "PRESS 5 -> TO ADD COURSE \n"
			+ "PRESS 6 -> TO REMOVE COURSE\n"
			+ "PRESS 7 -> TO VIEW COURSES\n";
	
	public void displayAdminMenu() {
		System.out.println(prompt);
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		AdminService as = new AdminService();
		int input;
		
		do {
			input = sc.nextInt();
			
			switch(input) {
			case 1 :
				as.approveStudent();
				break;
			case 2 :
				as.addProfessor();
				break;
			case 3 :
				as.generateReportCard(input, input, input);
				break;
			case 4 :
				as.addProfessor();
				break;
			case 5 :
				as.addCourse(input);
				break;
			case 6 :
				as.removeCourse(input);
				break;
			case 7 : 
				as.viewCourses();
				break;
			default:
				System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}
	
}
