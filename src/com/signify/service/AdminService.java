package com.signify.service;

import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotFoundException;
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
	public void addCourse(Course course) {
		AdminDAO adminDao = new AdminDAO();

		try {
			adminDao.addCourse(course);
		} catch (Exception e) {
			System.err.printf(e.getMessage());
		}
	}

	@Override
	public void removeCourse(String courseCode) {
		AdminDAO adminDao = new AdminDAO();

		try {
			adminDao.removeCourse(courseCode);
		} catch (CourseNotFoundException e) {
			System.err.printf(e.getMessage());
		}
	}

	@Override
	public void assignProfessorToCourse(String professorId, String courseCode) {
		AdminDAO adminDao = new AdminDAO();

		try {
			adminDao.assignProfessorToCourse(professorId, courseCode);
		} catch (CourseNotFoundException e) {
			System.err.printf(e.getMessage());
		}
	}

	@Override
	public Course getCourseDetails(String courseCode) {
		AdminDAO adminDao = new AdminDAO();
		Course course = null;
		try {
			course = adminDao.viewCourseDetails(courseCode);
		} catch (CourseNotFoundException e) {
			System.err.printf(e.getMessage());
		}
		return course;
	}

	@Override
	public double calculateCpi(String studentId) {
		AdminDAO adminDao = new AdminDAO();

		try {
			adminDao.calculateCpi(studentId);
		} catch (UserNotFoundException e) {
			System.err.printf(e.getMessage());
		}
		return 0;
	}

	@Override
	public List<Student> getUnapprovedStudents() {
		AdminDAO ad = new AdminDAO();
		List<Student> students = null;

		students = ad.getUnapprovedStudents();

		if(students == null) {
			System.err.println("[info]: No Unapproved Student found");
		}
		return students;
	}

	@Override
	public void addProfessor(Professor professor) {
		UserService userService = new UserService();
		userService.register(professor);
	}

	@Override
	public List<Professor> viewProfessors() {
		AdminDAO ad = new AdminDAO();
		List<Professor> professors = null;
		try {
			professors = ad.getProfessors();
		} catch (ProfessorNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return professors;
	}

	@Override
	public void addAdmin(Admin admin) {
		UserService userService = new UserService();
		userService.register(admin);
	}

	@Override
	public List<Admin> viewAdmins() {
		AdminDAO ad = new AdminDAO();
		List<Admin> admins = null;
		try {
			admins = ad.getAdmins();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return admins;
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
	public void approveStudentById(String studentId) {
		AdminDAO ad = new AdminDAO();
		try {
			ad.approveStudentById(studentId);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
