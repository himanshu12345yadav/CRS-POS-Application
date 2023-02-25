package com.signify.client;

import java.util.Scanner;

import com.signify.collection.UserSession;
import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorService;

public class CRSProfessorMenu {
	private String prompt = "\nPROFESSOR MENU\n-----------------\n1. VIEW ENROLLED STUDENTS\n"
			+ "2. ADD GRADES\n" + "3. -> LOGOUT \n";

	public void displayProfessorMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		ProfessorInterface profService = new ProfessorService();
		int input;

		do {
			System.out.println(prompt);
			input = sc.nextInt();

			switch (input) {
			case 1:
				System.out.println("Enter course id ");
				String courseId = sc.nextLine();
				profService.getEnrolledStudents(courseId);
				break;
			case 2:
				System.out.println("Enter Student Id : ");
				String studentId = sc.nextLine();
				System.out.println("Enter Course Code : ");
				String courseCode = sc.nextLine();
				System.out.println("Enter Grade");
				String grade = sc.nextLine();
				profService.addGrade(studentId, courseCode, grade);
				break;
			case 3:
				UserSession.user = null;
				flag = false;
				return;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
