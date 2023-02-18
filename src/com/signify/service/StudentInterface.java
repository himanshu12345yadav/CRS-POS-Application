package com.signify.service;

public interface StudentInterface {
	public void viewGrades();
	public void viewGrade(int courseId);
	public void viewRegisterCourses();
	public void addCourse();
	public void dropCourse(int courseId);
	public void payFees();
}
