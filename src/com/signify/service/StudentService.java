package com.signify.service;

import java.util.List;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.RegisteredCourse;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotApprovedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.jdbc.StudentDAO;

public class StudentService extends UserService implements StudentInterface {

	private StudentDAO dao = new StudentDAO();

	@Override
	public void register() {
		// ! CALL THE USER SERVICE REGISTATION
		throw new UnsupportedOperationException("Unimplemented method 'register'");
	}

	@Override
	public String getStudentId(String userId) {
		return dao.getStudentId(userId);
	}

	@Override
	public boolean getApprovedStatus(String studentId) throws StudentNotApprovedException {
		return dao.getApprovalStatus(studentId);
	}

	@Override
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException {
		return dao.getAvailableCourses(studentId);
	}

	@Override
	public void semesterRegister(String studentId, int semester) throws StudentNotRegisteredException {
		dao.semesterRegistration(studentId, semester);
	}

	@Override
	public void addCourse(String studentId, String courseCode, int type)
			throws CourseNotFoundException, CourseLimitExceedException {
		dao.addCourse(studentId, courseCode, type);
	}

	@Override
	public void dropCourse(String studentId, String courseCode) throws CourseNotFoundException {
		dao.dropCourse(studentId, courseCode);
	}

	@Override
	public List<RegisteredCourse> getRegisteredCourses(String studentId) {
		return dao.getRegisteredCourses(studentId);
	}

	@Override
	public List<RegisteredCourse> getGrades(String studentId) {
		return dao.getGrades(studentId);
	}

	@Override
	public List<Course> getFees(String studentId) {
		return dao.getFees(studentId);
	}

	@Override
	public void payFeesOffline(OfflinePayment payment) {
		dao.payFeesOffline(payment);
	}

	@Override
	public void payFeesOnline(OnlinePayment payment) {
		dao.payFeesOnline(payment);
	}

}
