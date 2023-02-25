package com.signify.service;

import java.util.List;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;

public interface StudentInterface {
	public void register();

	public String getStudentId(String userId);

	public boolean getApprovedStatus(String studentId);

	public List<Course> getAvailableCourses(String studentId);

	public void semesterRegister(String studentId, int semester);

	public void addCourse(String studentId, String courseCode, int type);

	public void dropCourse(String studentId, String courseCode);

	public List<RegisteredCourse> getRegisteredCourses(String studentId);

	public List<String> getGrades(String studentId);

	public List<Course> getFees(String studentId);

	public void payFeeByCard(OnlinePayment online, Payment payment);

	public void payFeeByNetBanking(OnlinePayment online, Payment payment);

	public void payfeeByCash(OfflinePayment offline, Payment payment);

	public void payFeeByCheque(OfflinePayment offline, Payment payment);
}
