package com.signify.service;

public interface AdminInterface {
	public void approveStudent(String studentUsername);
	public void addProfessor(String professorId);
	public void generateReportCard(String studentId , String courseId , int semester);
	public void assignProfessorToCourse(String professorId , String courseId);
	public void addCourse(String courseCode , String name , boolean isOffered , String instructor);
	public void removeCourse(String courseId);
	public void viewCourseDetails(String courseId);
	public double calculateCpi(String studentId);
}