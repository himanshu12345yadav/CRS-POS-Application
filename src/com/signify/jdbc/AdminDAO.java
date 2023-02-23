/**
 * 
 */
package com.signify.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.constants.DBConstants;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.ProfessorNotFoundException;
import com.signify.exception.UnApprovedStudentNotFoundException;
import com.signify.exception.UserNotFoundException;
import com.signify.utils.DBUtils;

public class AdminDAO implements IAdminDAO {

	public void addCourse(Course course) {
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st_prof = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.INSERT_COURSE_IN_CATALOG);
			st.setString(1, course.getCourseCode());
			st.setString(2, course.getName());
			st.setString(3, course.getInstructor());
			st.setDouble(4, course.getCoursefee());
			st.executeUpdate();

			st_prof = conn.prepareStatement(DBConstants.UPDATE_PROFESSOR_COURSE);
			st_prof.setString(1, course.getCourseCode());
			st_prof.setString(2, course.getInstructor());
			st_prof.executeUpdate();

		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void removeCourse(String courseCode) throws CourseNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.REMOVE_COURSE_FROM_CATALOG);
			st.setString(1, courseCode);
			st.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void assignProfessorToCourse(String professorid, String courseCode) throws CourseNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;
		PreparedStatement st_course = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.UPDATE_COURSE_OF_PROFESSOR);
			st.setString(1, courseCode);
			st.setString(2, professorid);
			st.executeUpdate();
			st.close();

			st_course = conn.prepareStatement(DBConstants.UPDATE_PROFESSOR_IN_CATALOG);
			st_course.setString(1, professorid);
			st_course.setString(2, courseCode);
			st_course.close();

			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public Course viewCourseDetails(String courseCode) throws CourseNotFoundException {
		Course course = new Course();
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.GET_COURSE_FROM_CATALOG);
			st.setString(1, courseCode);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				course.setCourseCode(rs.getString("coursecode"));
				course.setName(rs.getString("coursename"));
				course.setInstructor(rs.getString("instructor"));
				course.setCoursefee(rs.getDouble("coursefee"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	public List<Course> viewCourses() {
		List<Course> cs = new ArrayList<Course>();
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();

			st = conn.prepareStatement(DBConstants.LIST_COURSES);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseCode(rs.getString("coursecode"));
				c.setName(rs.getString("coursename"));
				c.setInstructor(rs.getString("instructor"));
				c.setCoursefee(rs.getDouble("coursefee"));
				cs.add(c);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}

	public List<Student> getUnapprovedStudents() throws UnApprovedStudentNotFoundException {
		List<Student> students = new ArrayList<Student>();

		Connection conn;
		PreparedStatement st;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.GET_UNAPPROVED_STUDENTS);
			st.setInt(1, 0);
			ResultSet rs = st.executeQuery();

			if (!rs.next()) {
				throw new UnApprovedStudentNotFoundException();
			}
			while (rs.next()) {
				Student s = new Student();
				s.setUserId(rs.getString("userid"));
				s.setStudentId(rs.getString("studentid"));
				s.setName(rs.getString("studentnname"));
				students.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	public void approveAllStudents() {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.APPROVE_ALL_STUDENTS);
			st.setInt(1, 1);
			st.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public void approveStudentById(String studentId) throws UserNotFoundException {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.APPROVE_BY_ID);
			st.setInt(1, 1);
			st.setString(2, studentId);
			int row = st.executeUpdate();

			if (row == 0) {
				throw new UserNotFoundException(studentId);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public List<Admin> getAdmins() {
		List<Admin> admins = new ArrayList<Admin>();
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.VIEW_ADMINS);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Admin admin = new Admin();
				admin.setUserId(rs.getString("adminid"));
				admin.setName(rs.getString("adminname"));
				admins.add(admin);
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return admins;
	}

	public List<Professor> getProfessors() throws ProfessorNotFoundException {
		List<Professor> professors = new ArrayList<Professor>();
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.VIEW_PROFESSORS);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Professor professor = new Professor();
				professor.setUserId(rs.getString("professorid"));
				professor.setName(rs.getString("professorname"));
				professor.setDesignation(rs.getString("designation"));
				professor.setDepartment(rs.getString("department"));
				professor.setCourse(rs.getString("course"));
				professors.add(professor);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return professors;
	}

	public double calculateCpi(String studentId) throws UserNotFoundException {

		String grade;
		double cgpa = 0.0, cpi = 0.0;
		int n = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtils.getConnection();
			st = conn.prepareStatement(DBConstants.GET_GRADES);
			st.setString(1, studentId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				grade = rs.getString("grade");
				n++;
				if (grade.equals("A+"))
					cgpa += 10.0;
				else if (grade.equals("A"))
					cgpa += 9.5;
				else if (grade.equals("A-"))
					cgpa += 8.5;
				else if (grade.equals("B"))
					cgpa += 8.0;
				else if (grade.equals("B-"))
					cgpa += 7.5;
				else if (grade.equals("C"))
					cgpa += 7.0;
				else if (grade.equals("C-"))
					cgpa += 6.5;
				else if (grade.equals("D"))
					cgpa += 6.0;
				else
					cgpa += 0;
			}

			cpi = cgpa / n;

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return cpi;
	}

	@Override
	public void generateReportCard(String studentId) throws UserNotFoundException {
		AdminDAO adminDao = new AdminDAO();

		try {
			adminDao.generateReportCard(studentId);
		} catch (UserNotFoundException e) {
			System.err.printf(e.getMessage());
		}
	}

}
