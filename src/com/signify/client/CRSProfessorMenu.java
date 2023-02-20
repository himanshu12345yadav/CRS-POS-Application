package com.signify.client;

import java.util.Scanner;

import com.signify.collection.UserSession;
import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorService;

public class CRSProfessorMenu {
	private String prompt = "\nPROFESSOR MENU\n-----------------\nPRESS 1 -> FOR VIEWING ENROLLED STUDENTS\n"
			+ "PRESS 2 -> TO ADD GRADES\n"
			+ "PRESS 3 -> LOGOUT \n";
	
	public void displayProfessorMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		ProfessorInterface profServ = new ProfessorService();
		int input;
		
		do {
			System.out.println(prompt);
			input = sc.nextInt();
			
			switch(input) {
			case 1 :
				System.out.println("Enter course id ");
				String courseId = sc.next();
				profServ.viewEnrolledStudents(courseId);
				break;
			case 2 :
				System.out.println("Enter Student Id : ");
				String studentId = sc.next();
				System.out.println("Enter Course Code : ");
				String courseCode = sc.next();
				System.out.println("Enter Grade");
				String grade = sc.next();
				profServ.addGrade(studentId , courseCode , grade);
				break;
			case 3:
				UserSession.user = null;
				return;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
