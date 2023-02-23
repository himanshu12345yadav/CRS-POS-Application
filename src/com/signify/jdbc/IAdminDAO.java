package com.signify.jdbc;

import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.UnApprovedStudentNotFoundException;
import com.signify.exception.UserNotFoundException;

public interface IAdminDAO {
	public void approveAllStudents();

	public void approveStudentById(String studentId) throws UserNotFoundException;

	public List<Student> getUnapprovedStudents() throws UnApprovedStudentNotFoundException;

	public List<Admin> getAdmins();

	public List<Professor> getProfessors() throws ProfessorNotFoundException;

	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotFoundException;

	public void addCourse(Course course);

	public void removeCourse(String courseCode) throws CourseNotFoundException;

	public Course viewCourseDetails(String courseCode) throws CourseNotFoundException;

	public double calculateCpi(String studentId) throws UserNotFoundException;

	public void generateReportCard(String studentId) throws UserNotFoundException;
}
