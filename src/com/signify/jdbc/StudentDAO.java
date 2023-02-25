package com.signify.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.result.SqlTimestampValueFactory;
import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.constants.DBConstants;
import com.signify.exception.CourseLimitExceedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.utils.DBUtils;

public class StudentDAO implements IStudentDAO {

	@Override
	public void register(Student student) {

	}

	@Override
	public void semesterRegistration(String studentId, int semester) throws StudentNotRegisteredException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtRegisterSemester = conn.prepareStatement(DBConstants.SEMESTER_REGISTRATION)) {
			stmtRegisterSemester.setInt(1, semester);
			stmtRegisterSemester.setString(2, studentId);

			int rows = stmtRegisterSemester.executeUpdate();

			if (rows == 0) {
				throw new StudentNotRegisteredException(studentId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getStudentId(String userId) {
		Connection conn = DBUtils.getConnection();

		String studentId = null;

		try (PreparedStatement stmtGetStudentID = conn.prepareStatement(DBConstants.GET_STUDENT_ID);) {
			stmtGetStudentID.setString(1, userId);
			ResultSet rs = stmtGetStudentID.executeQuery();

			while (rs.next()) {
				studentId = rs.getString("studentid");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentId;
	}

	@Override
	public boolean getApprovalStatus(String studentId) {
		Connection conn = DBUtils.getConnection();
		boolean isapproved = false;

		try (PreparedStatement stmtGetApprovalStatus = conn.prepareStatement(DBConstants.GET_APPROVAL_STATUS)) {
			stmtGetApprovalStatus.setString(1, studentId);

			ResultSet rs = stmtGetApprovalStatus.executeQuery();

			while (rs.next()) {
				isapproved = rs.getInt(1) == 1 ? true : false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isapproved;
	}

	@Override
	public List<Course> getAvailableCourses(String studentId) throws SemesterNotRegisteredException {
		Connection conn = DBUtils.getConnection();
		List<Course> courses = new ArrayList<Course>();

		try (PreparedStatement stmtGetStudentID = conn.prepareStatement(DBConstants.GET_SEMESTER);
				PreparedStatement stmtGetAvailableCourses = conn.prepareStatement(DBConstants.GET_AVAILABLE_COURSES);) {
			stmtGetStudentID.setString(1, studentId);
			ResultSet rs = stmtGetStudentID.executeQuery();
			rs.next();

			if (rs.wasNull()) {
				throw new SemesterNotRegisteredException(studentId);
			}

			int semester = rs.getInt("semester");

			while (rs.next()) {
				studentId = rs.getString("studentid");
			}

			stmtGetAvailableCourses.setInt(1, semester);
			ResultSet rsCourses = stmtGetAvailableCourses.executeQuery();

			while (rsCourses.next()) {
				Course course = new Course();
				course.setCourseCode(rs.getString("coursecode"));
				course.setName(rs.getString("coursename"));
				course.setInstructor(rs.getString("instructor"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public void addCourse(String studentId, String courseCode)
			throws CourseNotFoundException, CourseLimitExceedException {

	}

	@Override
	public void dropCourse(String studentId, String courseCode) throws CourseNotFoundException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement dropStudentCourse = conn.prepareStatement(DBConstants.DROP_STUDENT_COURSE)) {
			dropStudentCourse.setString(1, courseCode);
			dropStudentCourse.setString(2, studentId);

			int rows = dropStudentCourse.executeUpdate();

			if (rows == 0) {
				throw new CourseNotFoundException(courseCode);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RegisteredCourse> getGrades(String studentId) {
		Connection conn = DBUtils.getConnection();
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();

		try (PreparedStatement getStudentGrades = conn.prepareStatement(studentId)) {
			getStudentGrades.setString(1, studentId);
			ResultSet rs = getStudentGrades.executeQuery(DBConstants.GET_STUDENT_GRADES);
			// ! Check this query

			while (rs.next()) {
				RegisteredCourse course = new RegisteredCourse();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				String grades = rs.getString("grade");
				course.setCourseName(coursename);
				course.setCourseCode(coursecode);
				course.setCourseName(coursename);
				course.setGrade(grades);
				courses.add(course);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public List<RegisteredCourse> getRegisteredCourses(String studentId) {
		Connection conn = DBUtils.getConnection();
		List<RegisteredCourse> courses = new ArrayList<RegisteredCourse>();

		try (PreparedStatement stmtGetRegisteredCourse = conn.prepareStatement(DBConstants.GET_REGISTERED_COURSE)) {
			stmtGetRegisteredCourse.setString(1, studentId);
			ResultSet rs = stmtGetRegisteredCourse.executeQuery();

			while (rs.next()) {
				RegisteredCourse course = new RegisteredCourse();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				int type = rs.getInt("type");

				course.setCourseCode(coursecode);
				course.setCourseName(coursename);
				course.setType(type);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public List<Course> getFees(String studentId) {
		Connection conn = DBUtils.getConnection();

		List<Course> courses = new ArrayList<Course>();

		try (PreparedStatement stmtGetFees = conn.prepareStatement(DBConstants.GET_FEES)) {
			stmtGetFees.setString(1, studentId);

			ResultSet rs = stmtGetFees.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				String coursecode = rs.getString("coursecode");
				String coursename = rs.getString("coursename");
				int fee = rs.getInt("coursefee");
				course.setCourseCode(coursecode);
				course.setName(coursename);
				course.setFee(fee);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return courses;
	}

	@Override
	public void payFeesByCheque(OfflinePayment offline, Payment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByCheque = conn.prepareStatement(DBConstants.PAY_FEE_BY_CHEQUE);
				PreparedStatement stmtPayFeeOffline = conn.prepareStatement(DBConstants.PAY_FEE_OFFLINE)) {

			stmtPayFeeByCheque.setString(1, payment.getReferenceId());
			stmtPayFeeByCheque.setString(2, payment.getStudentId());
			stmtPayFeeByCheque.setString(3, payment.getMode());
			stmtPayFeeByCheque.setString(4, "PAID");
			stmtPayFeeByCheque.setDouble(5, payment.getAmount());

			stmtPayFeeByCheque.execute();

			stmtPayFeeOffline.setString(1, offline.getReferenceId());
			stmtPayFeeOffline.setInt(2, offline.isCash() == true ? 1 : 0);
			stmtPayFeeOffline.setInt(3, offline.getChequeNumber());
			stmtPayFeeOffline.setString(4, offline.getBankName());

			stmtPayFeeOffline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void payFeesByCash(OfflinePayment offline, Payment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByCash = conn.prepareStatement(DBConstants.PAY_FEE_BY_CASH);
				PreparedStatement stmtPayOffline = conn.prepareStatement(DBConstants.PAY_FEE_OFFLINE)) {

			stmtPayFeeByCash.setString(1, payment.getReferenceId());
			stmtPayFeeByCash.setString(2, payment.getStudentId());
			stmtPayFeeByCash.setString(3, payment.getMode());
			stmtPayFeeByCash.setString(4, "PAID");
			stmtPayFeeByCash.setDouble(5, payment.getAmount());
			stmtPayFeeByCash.executeUpdate();

			stmtPayOffline.setString(1, offline.getReferenceId());
			stmtPayOffline.setInt(2, offline.isCash() == true ? 1 : 0);
			stmtPayOffline.setInt(3, offline.getChequeNumber());
			stmtPayOffline.setString(4, offline.getBankName());
			// ! check this query
			stmtPayOffline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void payFeesByCard(OnlinePayment online, Payment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByCard = conn.prepareStatement(DBConstants.PAY_FEE_BY_CARD);
				PreparedStatement stmtPayFeeOnline = conn.prepareStatement(DBConstants.PAY_FEE_ONLINE)) {

			stmtPayFeeByCard.setString(1, payment.getReferenceId());
			stmtPayFeeByCard.setString(2, payment.getStudentId());
			stmtPayFeeByCard.setString(3, payment.getMode());
			stmtPayFeeByCard.setString(4, "PAID");
			stmtPayFeeByCard.setDouble(5, payment.getAmount());

			stmtPayFeeByCard.execute();

			stmtPayFeeOnline.setString(1, online.getReferenceId());
			stmtPayFeeOnline.setInt(2, online.isCard() == true ? 1 : 0);
			stmtPayFeeOnline.setString(3, online.getCardNumber());
			stmtPayFeeOnline.setString(4, online.getCardType());
			stmtPayFeeOnline.setString(5, online.getModeOfTransfer());
			stmtPayFeeOnline.setString(6, online.getAccountNumber());
			stmtPayFeeOnline.setString(7, online.getIfsccode());

			stmtPayFeeOnline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void payFeesByNetBanking(OnlinePayment online, Payment payment) {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtPayFeeByNetBanking = conn.prepareStatement(DBConstants.PAY_FEE_BY_NETBANKING);
				PreparedStatement stmtPayFeeOnline = conn.prepareStatement(DBConstants.PAY_FEE_ONLINE)) {

			stmtPayFeeByNetBanking.setString(1, payment.getReferenceId());
			stmtPayFeeByNetBanking.setString(2, payment.getStudentId());
			stmtPayFeeByNetBanking.setString(3, payment.getMode());
			stmtPayFeeByNetBanking.setString(4, "PAID");
			stmtPayFeeByNetBanking.setDouble(5, payment.getAmount());

			stmtPayFeeByNetBanking.execute();

			stmtPayFeeOnline.setString(1, online.getReferenceId());
			stmtPayFeeOnline.setInt(2, online.isCard() == true ? 1 : 0);
			stmtPayFeeOnline.setString(3, online.getCardNumber());
			stmtPayFeeOnline.setString(4, online.getCardType());
			stmtPayFeeOnline.setString(5, online.getModeOfTransfer());
			stmtPayFeeOnline.setString(6, online.getAccountNumber());
			stmtPayFeeOnline.setString(7, online.getIfsccode());

			stmtPayFeeOnline.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
