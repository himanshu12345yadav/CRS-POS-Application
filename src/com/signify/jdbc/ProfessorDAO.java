package com.signify.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
import com.signify.bean.Student;
import com.signify.constants.DBConstants;
import com.signify.exception.ProfessorNotAssignedException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.utils.DBUtils;

public class ProfessorDAO implements IProfessorDAO {

	@Override
	public List<Student> getEnrolledStudents(String professorId) throws ProfessorNotAssignedException {
		Connection conn = null;
		List<Student> students = new ArrayList<Student>();
		conn = DBUtils.getConnection();

		try (PreparedStatement stmtGetCourse = conn.prepareStatement(DBConstants.GET_PROFESSOR_COURSE);
				PreparedStatement stmtGetStudents = conn.prepareStatement(DBConstants.GET_ENROLLED_STUDENTS)) {
			stmtGetCourse.setString(1, professorId);
			ResultSet rs = stmtGetCourse.executeQuery();
			String coursecode = rs.getString("course");

			if (rs.wasNull()) {
				throw new ProfessorNotAssignedException(professorId);
			}

			while (rs.next()) {
				Student st = new Student();
				st.setStudentId(rs.getString("studentid"));
				students.add(st);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public void addGrades(String professorId, String studentId, String grade)
			throws StudentNotRegisteredException, ProfessorNotAssignedException {
		Connection conn = DBUtils.getConnection();

		try (PreparedStatement stmtGetProfessorCourse = conn.prepareStatement(DBConstants.GET_PROFESSOR_COURSE);
				PreparedStatement stmtGradeStudent = conn.prepareStatement(DBConstants.UPDATE_STUDENT_GRADE);) {

			stmtGetProfessorCourse.setString(1, "professorid");
			ResultSet rs = stmtGetProfessorCourse.executeQuery();

			String courseCode = rs.getString("course");

			if (rs.wasNull()) {
				throw new ProfessorNotAssignedException(professorId);
			}

			stmtGradeStudent.setString(1, grade);
			stmtGradeStudent.setString(2, studentId);
			stmtGradeStudent.setString(3, courseCode);

			int rows = stmtGradeStudent.executeUpdate();

			if (rows == 0) {
				throw new StudentNotRegisteredException(studentId);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
