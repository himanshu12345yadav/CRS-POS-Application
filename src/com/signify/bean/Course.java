package com.signify.bean;

public class Course {
	private String courseCode, courseName, instructor;
	private double fee;

	public Course(String courseCode, String courseName, String instructor, double coursefee) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.instructor = instructor;
		this.fee = coursefee;
	}

	public Course() {
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double coursefee) {
		this.fee = coursefee;
	}
}
