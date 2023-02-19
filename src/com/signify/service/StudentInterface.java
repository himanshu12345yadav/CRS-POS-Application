package com.signify.service;

public interface StudentInterface {
	public void viewGrades();
	public void viewGrade(String courseId);
	public void viewRegisterCourses();
	public void addCourse(String courseId);
	public void dropCourse(String courseId);
	public void payFees();
}
