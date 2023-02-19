package com.signify.client;

import java.util.Scanner;
import com.signify.service.AdminService;

public class CRSAdminMenu {
	private String prompt = "\nADMIN MENU\n------------\nPRESS 1 -> FOR APPROVING STUDENT \n"
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
			System.out.println(prompt);
			int input = sc.nextInt();
			
			switch(input) {
			case 1 :
				as.approveStudent("1");
				break;
			case 2 :
				System.out.print("Enter Professor Id : ");
				String profId = sc.next();
				as.addProfessor(profId);
				break;
			case 3 :
				as.generateReportCard("1", "1", 1);
				break;
			case 4 :
				System.out.println("Professor Id : ");
				String courseProfId = sc.next();
				System.out.println("Course Id : ");
				String CourseId = sc.next();
				as.assignProfessorToCourse(CourseId , courseProfId);
				break;
			case 5 :
				System.out.print("Course Code :");
				String courseCode = sc.next();
				System.out.print("Name : ");
				String name = sc.next();
				System.out.print("isOffered : ");
				boolean isOffered = sc.nextBoolean();
				System.out.print("Intructor : ");
				String instructor = sc.next();
				as.addCourse(courseCode , name , isOffered , instructor);
				break;
			case 6 :
				System.out.print("Course Code : ");
				String removeCourseCode = sc.next();
				as.removeCourse(removeCourseCode);
				break;
			case 7 : 
				System.out.println("Course Code : ");
				String courseCodeDetails = sc.next();
				as.viewCourseDetails(courseCodeDetails);
				break;
			case 8:
				return;
			default:
				System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}
	
}
