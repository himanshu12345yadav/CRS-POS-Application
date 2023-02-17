package com.signify.service;

import com.signify.client.CRSStudentMenu;

public class StudentService extends UserService implements StudentInterface {
	
	public void register() {
		System.out.println("\nREGISTERATION FOR STUDENT COMPLETED");
		CRSStudentMenu cs = new CRSStudentMenu();
		cs.displayStudentMenu();
	}
	public void viewGradeCard() {
		System.out.println("Grade Card viewed");
	}
	public void viewRegisteredCouse() {
		System.out.println("View Registered Course");
	}
	public void addCourse() {
		System.out.println("Course Added");
	}
	public void dropCourse() {
		System.out.println("course droppped");
	}
	public void payFee() {
		System.out.println("Fees is paid");
	}
}
