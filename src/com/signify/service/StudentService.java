package com.signify.service;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;
import java.util.List;

public class StudentService extends UserService implements StudentInterface {

	@Override
	public void register() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'register'");
	}

	@Override
	public String getStudentId(String userId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getStudentId'");
	}

	@Override
	public boolean getApprovedStatus(String studentId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getApprovedStatus'");
	}

	@Override
	public List<Course> getAvailableCourses(String studentId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAvailableCourses'");
	}

	@Override
	public void semesterRegister(String studentId, int semester) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'semesterRegister'");
	}

	@Override
	public void addCourse(String studentId, String courseCode, int type) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'addCourse'");
	}

	@Override
	public void dropCourse(String studentId, String courseCode) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'dropCourse'");
	}

	@Override
	public List<RegisteredCourse> getRegisteredCourses(String studentId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRegisteredCourses'");
	}

	@Override
	public List<String> getGrades(String studentId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getGrades'");
	}

	@Override
	public List<Course> getFees(String studentId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getFees'");
	}

	@Override
	public void payFeeByCard(OnlinePayment online, Payment payment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'payFeeByCard'");
	}

	@Override
	public void payFeeByNetBanking(OnlinePayment online, Payment payment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'payFeeByNetBanking'");
	}

	@Override
	public void payfeeByCash(OfflinePayment offline, Payment payment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'payfeeByCash'");
	}

	@Override
	public void payFeeByCheque(OfflinePayment offline, Payment payment) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'payFeeByCheque'");
	}

}
