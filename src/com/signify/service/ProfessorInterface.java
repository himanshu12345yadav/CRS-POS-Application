package com.signify.service;

import java.util.List;

import com.signify.bean.Student;

public interface ProfessorInterface {
	public List<Student> getEnrolledStudents(String professorId);
	public void addGrade(String professorId , String studentId , String grade);
}
