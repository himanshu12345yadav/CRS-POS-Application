package com.signify.service;

import com.signify.client.CRSAdminMenu;

public class AdminService extends UserService implements AdminInterface {
	
	public void register() {
		System.out.println("\nREGISTERATION FOR ADMIN COMPLETED");
		CRSAdminMenu adminMenu = new CRSAdminMenu();
		adminMenu.displayAdminMenu();
	}
	public void addProfessor() {
		System.out.println("Professor added successfully");
	}
	public void assignCourse() {
		System.out.println("Course assigned successfully");
	}
	public void approveStudent() {
		System.out.println("Student approved");
	}
	public void generateReportCard(int studentId , int courseId , int semester) {
		System.out.println("Report card generated");
	}
	public void addCourse(int courseId) {
		System.out.println("course added");
	}
	public void removeCourse(int courseId) {
		System.out.println("Course removed successfully");
	}
	public void viewCourses() {
		System.out.println("Viewing Courses");
	}
	public int generateCPI() {
		System.out.println("Generate CPI");
		return 1;
	}
}
