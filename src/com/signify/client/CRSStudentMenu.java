package com.signify.client;

import java.util.Scanner;

import com.signify.service.StudentService;

public class CRSStudentMenu {
	private String prompt = "PRESS 1 -> TO VIEW GRADES\n"
			+ "PRESS 2 -> TO VIEW GRADE FOR A PARTICULAR COURSE\n"
			+ "PRESS 3 -> TO VIEW REGISTERED COURSE\n"
			+ "PRESS 4 -> TO ADD COURSE\n"
			+ "PRESS 5 -> TO DROP A COURSE\n"
			+ "PRESS 6 -> TO PAY FEES\n"
			+ "PRESS 7 -> GO TO MAIN MENU";
	
	public void displayStudentMenu() {
		System.out.println(prompt);
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		StudentService ss = new StudentService();
		int input;
		
		do {
			input = sc.nextInt();
			
			switch(input) {
			case 1 :
				ss.viewGrades();
				break;
			case 2 :
				ss.viewGrade(10);
				break;
			case 3 :
				ss.viewRegisterCourses();
				break;
			case 4 :
				ss.addCourse();
				break;
			case 5 :
				ss.dropCourse(10);
				break;
			case 6 :
				ss.payFees();
				break;
			case 7:
				return;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
