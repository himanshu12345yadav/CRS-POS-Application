package com.signify.client;

import java.util.Scanner;

import com.signify.collection.UserSession;
import com.signify.service.StudentService;

public class CRSStudentMenu {
	private String prompt = "\nSTUDENT MENU\n--------------\nPRESS 1 -> TO VIEW GRADES\n"
			+ "PRESS 2 -> TO VIEW GRADE FOR A COURSE\n"
			+ "PRESS 3 -> TO VIEW REGISTERED COURSE\n"
			+ "PRESS 4 -> TO ADD COURSE\n"
			+ "PRESS 5 -> TO DROP A COURSE\n"
			+ "PRESS 6 -> TO PAY FEES\n"
			+ "PRESS 7 -> TO LOGOUT";
	
	public void handleFees(StudentService ss) {
		Scanner sc = new Scanner(System.in);
		int mode = sc.nextInt();
		
		if(mode == 1) {
			System.out.println("SELECT ONLINE PAYMENT METHOD \nPRESS 1 FOR CARD \nPRESS 2 FOR NETBANKING");
			int method = sc.nextInt();
			
			if(method == 1) {
				// card method selected
				System.out.print("Enter Card Number : ");
				String cardNumber = sc.next();
				System.out.println("Enter Card Type : ");
				String cardType = sc.next();
				ss.payFees();
			}
			else if(method == 2) {
				// net banking selected
				System.out.print("Enter mode of transfer : ");
				String modeOfTransfer = sc.next();
				System.out.println("Enter Account Number : ");
				String accountNumber = sc.next();
				System.out.println("Enter IFSC Code");
				String ifscCode = sc.next();
				
			}
			// handle online fees
		}
		else if(mode == 2) {
			// handle offline fees
			System.out.println("SELECT OFFLINE PAYMENT METHOD \nPRESS 1 FOR CASH \nPRESS 2 FOR CHEQUE");
			int method = sc.nextInt();
			
			if(method == 1) {
				// handle cash payment
			}
			else if(method == 2) {
				// handle cheque payment
			}
		}
		else {
			System.out.println("Invalid Input");
			return;
		}
	}
	public void displayStudentMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		StudentService ss = new StudentService();
		int input;
		
		do {
			System.out.println(prompt);
			input = sc.nextInt();
			
			switch(input) {
			case 1 :
				ss.viewGrades();
				break;
			case 2 :
				System.out.println("Enter Course Id :");
				String courseId = sc.next();
				ss.viewGrade(courseId);
				break;
			case 3 :
				ss.viewRegisterCourses();
				break;
			case 4 :
				System.out.print("Enter Course Id to Add :");
				String addCourseId = sc.next();
				System.out.print("Enter Semester :");
				String semester = sc.next();
				ss.addCourse(addCourseId , semester);
				break;
			case 5 :
				System.out.print("Enter Course Id to Drop :");
				String dropCourseId = sc.next();
				ss.dropCourse(dropCourseId);
				break;
			case 6 :
				System.out.println("Paying Fees");
				System.out.println("SELECT THE MODE OF PAYMENT\nPRESS 1 FOR ONLINE \n PRESS 2 FOR OFFLINE");
				handleFees(ss);
				break;
			case 7:
				UserSession.user = null;
				return;
			default:
				System.out.println("Wrong input : Try again");
				break;
			}
		} while (flag);
	}
}
