package com.signify.service;

import com.signify.collection.UserData;

public class StudentService extends UserService implements StudentInterface {
	@Override
	public void viewGrades() {
		System.out.println("Grade Card viewed");
	}
	@Override
	public void viewRegisterCourses() {
		System.out.println("View Registered Course");
	}
	@Override
	public void addCourse() {
		System.out.println("Course Added");
	}
	@Override
	public void dropCourse(int courseId) {
		System.out.println("Dropping course , Course ID -> " + courseId);	
	}
	@Override
	public void payFees() {
		System.out.println("Fees is paid");
	}
	@Override
	public void viewGrade(int courseId) {
		System.out.println("Viewing Course Grades , Course Id -> " + courseId);		
	}
}
