package com.signify.bean;

public class Course {
	private String courseCode, name, instructor;
	private double fee;

	public Course(String courseCode, String name, String instructor, double coursefee) {
		super();
		this.courseCode = courseCode;
		this.name = name;
		this.instructor = instructor;
		this.fee = fee;
	}

	public Course() {
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
