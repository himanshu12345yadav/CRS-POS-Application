package com.signify.service;

public interface ProfessorInterface {
	public void viewEnrolledStudents(String courseId);
	public void addGrade(String studentId , String courseCode , String grade);
}
