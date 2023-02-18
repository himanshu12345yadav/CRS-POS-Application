package com.signify.service;

public class ProfessorService extends UserService implements ProfessorInterface {
	@Override
	public void viewEnrolledStudents() {
		System.out.println("View enrolled Students");
	}
	@Override
	public void addGrade() {
		System.out.println("Added grades");
	}
}
