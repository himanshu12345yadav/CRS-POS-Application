package com.signify.service;

import java.util.List;

import com.signify.bean.Student;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.jdbc.ProfessorDAO;

public class ProfessorService extends UserService implements ProfessorInterface {

	private ProfessorDAO dao = new ProfessorDAO();

	@Override
	public List<Student> getEnrolledStudents(String professorId) {
		List<Student> students = null;

		try {
			students = dao.getEnrolledStudents(professorId);
		} catch (ProfessorNotAssignedException e) {
			System.out.println(e.getMessage());
		}

		return students;
	}

	@Override
	public void addGrade(String professorId, String studentId, String grade) {
		try {
			dao.addGrades(professorId, studentId, grade);
		} catch (StudentNotRegisteredException | ProfessorNotAssignedException e) {
			System.out.println(e.getMessage());
		}
	}

}
