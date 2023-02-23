package com.signify.bean;

import java.util.Date;

public class Professor extends User {

	private String department, designation, course;
	private Date doj;

	public Professor(String userId, String username) {
		super(userId, username);
	}

	public Professor() {
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
