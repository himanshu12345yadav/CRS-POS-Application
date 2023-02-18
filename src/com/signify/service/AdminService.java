package com.signify.service;


public class AdminService extends UserService implements AdminInterface {
	@Override
	public void generateReportCard(int studentId , int courseId , int semester) {
		System.out.println("Report card generated");
	}
	@Override
	public void addCourse(int courseId) {
		System.out.println("course added");
	}
	@Override
	public void removeCourse(int courseId) {
		System.out.println("Course removed successfully");
	}
	@Override
	public void approveStudent(int studentId) {
		// TODO Auto-generated method stub
		System.out.println("Student with Student ID approved -> " + studentId);
	}
	@Override
	public void addProfessor(int professorId) {
		// TODO Auto-generated method stub
		System.out.println("Professor with Professor Id added -> " + professorId);
	}
	@Override
	public void assignProfessorToCourse(int professorId, int courseId) {
		// TODO Auto-generated method stub
		System.out.println("Professor with " + professorId + " is assigned to the Course " + courseId);
	}
	@Override
	public void viewCourseDetails(int courseId) {
		// TODO Auto-generated method stub
		System.out.println("Course Details with Course Id -> " + courseId);
	}
	@Override
	public double calculateCpi(int studentId) {
		// TODO Auto-generated method stub
		System.out.println("Calculating the cpi");
		return 0;
	}
}
