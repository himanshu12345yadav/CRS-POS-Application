package com.signify.client;

import java.util.Scanner;

import com.signify.service.ProfessorInterface;
import com.signify.service.ProfessorService;

public class CRSProfessorMenu {
	private String prompt = "PRESS 1 -> FOR VIEWING ENROLLED STUDENTS\n"
			+ "PRESS 2 -> TO ADD GRADES"
			+ "PRESS 3 -> GO TO MAIN MENU\n";
	
	public void displayProfessorMenu() {
		System.out.println(prompt);
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		ProfessorInterface profServ = new ProfessorService();
		int input;
		
		do {
			input = sc.nextInt();
			
			switch(input) {
			case 1 :
				profServ.viewEnrolledStudents();
				break;
			case 2 :
				profServ.addGrade();
				break;
			case 3:
				return;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
