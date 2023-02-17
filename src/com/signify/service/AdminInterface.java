package com.signify.service;

public interface AdminInterface {
	public void assignCourse();
	public void approveStudent();
	public void generateReportCard(int studentId , int courseId , int semester);
	public void addCourse(int courseId);
	public void removeCourse(int courseId);
	public void viewCourses();
}
