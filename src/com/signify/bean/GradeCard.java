package com.signify.bean;

import java.util.List;

public class GradeCard {
	private int studentId , semester;
	private float cpi;
	private List<RegisteredCourse> registeredCourses;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public float getCpi() {
		return cpi;
	}
	public void setCpi(float cpi) {
		this.cpi = cpi;
	}
	public List<RegisteredCourse> getRegisteredCourses() {
		return registeredCourses;
	}
	public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
}
