package com.signify.client;

import java.util.Scanner;

import com.signify.service.StudentService;

public class CRSStudentMenu {
	private String prompt = "PRESS 1 -> TO VIEW GRADES"
			+ "PRESS 2 -> TO VIEW GRADE FOR A PARTICULAR COURSE"
			+ "PRESS 3 -> TO VIEW REGISTERED COURSE"
			+ "PRESS 4 -> TO ADD COURSE"
			+ "PRESS 5 -> TO DROP A COURSE"
			+ "PRESS 6 -> TO PAY FEES";
	
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
				ss.viewGradeCard();
				break;
			case 2 :
				ss.viewGradeCard();
				break;
			case 3 :
				ss.viewRegisteredCouse();
				break;
			case 4 :
				ss.addCourse();
				break;
			case 5 :
				ss.dropCourse();
				break;
			case 6 :
				ss.payFee();
				break;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
