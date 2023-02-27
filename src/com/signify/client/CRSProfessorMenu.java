package com.signify.client;

import java.util.List;
import java.util.Scanner;

import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.collection.UserSession;
import com.signify.service.ProfessorService;

public class CRSProfessorMenu {
	private String prompt = "\nPROFESSOR MENU\n-----------------\n1. VIEW ENROLLED STUDENTS\n"
			+ "2. ADD GRADES\n" + "3. -> LOGOUT \n";

	public void displayProfessorMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		ProfessorService profService = new ProfessorService();
		int input;
		Professor professor = (Professor) UserSession.getUser();
		String professorId = professor.getUserId();

		do {
			System.out.println(prompt);
			input = Integer.parseInt(sc.nextLine());

			switch (input) {
				case 1:
					List<Student> students = profService.getEnrolledStudents(professorId);
					System.out.println("List of Enrolled Students\n-------------------");

					for (Student i : students) {
						System.out.println(i.getStudentId());
					}
					break;
				case 2:
					System.out.print("Enter Student Id : ");
					String studentId = sc.nextLine();
					System.out.print("Enter Grade : ");
					String grade = sc.nextLine();
					profService.addGrade(professorId, studentId, grade);
					System.out.println("\nStudent Graded");
					break;
				case 3:
					UserSession.clearSession();
					flag = false;
					return;
				default:
					System.out.println("Wrong input : Try again");
					break;
			}
		} while (flag);
	}
}
