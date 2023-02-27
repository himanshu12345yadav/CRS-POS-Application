package com.signify.service;

import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.CourseNotRegisteredException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.jdbc.AdminDAO;

public class AdminService extends UserService implements AdminInterface {
	@Override
	public void generateReportCard(String studentId) {
		AdminDAO adminDao = new AdminDAO();
		try {
			adminDao.generateReportCard(studentId);
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void addCourse(Course course) throws CourseNotAssignedException {
		AdminDAO adminDao = new AdminDAO();
		adminDao.addCourse(course);
	}

	@Override
	public void removeCourse(String courseCode) throws CourseNotFoundException {
		AdminDAO adminDao = new AdminDAO();

		adminDao.removeCourse(courseCode);
	}

	@Override
	public void assignProfessorToCourse(String professorId, String courseCode) throws CourseNotAssignedException {
		AdminDAO adminDao = new AdminDAO();
		adminDao.assignProfessorToCourse(professorId, courseCode);
	}

	@Override
	public Course getCourseDetails(String courseCode) throws CourseNotFoundException {
		AdminDAO adminDao = new AdminDAO();
		return adminDao.getCourseDetails(courseCode);
	}

	@Override
	public double calculateCpi(String studentId) throws UserNotFoundException, CourseNotRegisteredException {
		AdminDAO adminDao = new AdminDAO();

		return adminDao.calculateCpi(studentId);

	}

	@Override
	public List<Student> getUnapprovedStudents() {
		AdminDAO ad = new AdminDAO();
		List<Student> students = null;

		students = ad.getUnapprovedStudents();

		return students;
	}

	@Override
	public void addProfessor(Professor professor) throws UserAlreadyExistsException {
		UserService userService = new UserService();
		userService.register(professor);
	}

	@Override
	public List<Professor> getProfessors() {
		AdminDAO ad = new AdminDAO();
		return ad.getProfessors();
	}

	@Override
	public void addAdmin(Admin admin) throws UserAlreadyExistsException {
		UserService userService = new UserService();
		userService.register(admin);
	}

	@Override
	public List<Admin> getAdmins() {
		AdminDAO ad = new AdminDAO();
		return ad.getAdmins();
	}

	@Override
	public void approveAllStudents() {
		AdminDAO ad = new AdminDAO();
		try {
			ad.approveAllStudents();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void approveStudentById(String studentId) throws UserNotFoundException {
		AdminDAO ad = new AdminDAO();

		ad.approveStudentById(studentId);
	}
}
