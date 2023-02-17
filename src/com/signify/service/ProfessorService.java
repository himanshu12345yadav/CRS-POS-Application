package com.signify.service;

import com.signify.client.CRSProfessorMenu;

public class ProfessorService extends UserService implements ProfessorInterface {
	public void register() {
		System.out.println("\nREGISTERATION FOR PROFESSOR COMPLETED");
		CRSProfessorMenu pfm = new CRSProfessorMenu();
		pfm.displayProfessorMenu();
	}
	public void viewEnrolledStudents() {
		System.out.println("View enrolled Students");
	}
	public void addGrade() {
		System.out.println("Added grades");
	}
}
