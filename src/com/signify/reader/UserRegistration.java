/**
 * 
 */
package com.signify.reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import com.signify.bean.Admin;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.constants.DBConstants;

/**
 * @author Training
 *
 */
public class UserRegistration {
	public Student registerStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Password : ");
		String password = sc.nextLine();
		System.out.print("Enter Address : ");
		String address = sc.nextLine();
		System.out.print("Enter Age : ");
		int age = Integer.parseInt(sc.nextLine());
		System.out.print("Enter Branch : ");
		String branch = sc.nextLine();
		System.out.print("Enter Blood Group : ");
		String bg = sc.nextLine();
		System.out.print("Enter Father's Name : ");
		String fName = sc.nextLine();
		System.out.print("Enter phone Number : ");
		String phNum = sc.nextLine();
		String doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String userid = UUID.randomUUID().toString();
		String studentid = UUID.randomUUID().toString();

		Student student = new Student(studentid, name, branch, age, bg, fName, phNum,
				0);
		student.setUserId(userid);
		student.setAddress(address);
		student.setDOJ(doj);
		student.setUsername(name);
		student.setPassword(password);
		student.setRoleId(DBConstants.STUDENT_ROLE_ID);
		return student;
	}

	public User registerUser(int roleid) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Name : ");
		String name = sc.nextLine();
		System.out.print("Enter Password : ");
		String password = sc.nextLine();
		System.out.print("Enter Address : ");
		String address = sc.nextLine();

		System.out.print("Enter Date of joining (YYYY-MM-DD) or press 1 for Today : ");
		String dateFormat = sc.nextLine();
		String doj = "";

		if (dateFormat.equals("1")) {
			doj = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} else {
			try {
				doj = (new SimpleDateFormat("yyyy-MM-dd")).parse(doj).toString();
			} catch (ParseException e) {
				System.err.println("Wrong date format provided , hence taking today");
			}
		}

		// ! Handle Date inputs
		String userid = UUID.randomUUID().toString();

		if (roleid == DBConstants.ADMIN_ROLE_ID) {
			Admin admin = new Admin(userid, name);
			admin.setRoleid(DBConstants.ADMIN_ROLE_ID);
			admin.setPassword(password);
			admin.setAddress(address);
			admin.setDOJ(doj);

			return admin;
		} else if (roleid == DBConstants.PROFESSOR_ROLE_ID) {
			System.out.print("Enter Department : ");
			String department = sc.nextLine();
			System.out.print("Enter Designation : ");
			String designation = sc.nextLine();

			Professor prof = new Professor(userid, name);
			prof.setRoleid(DBConstants.PROFESSOR_ROLE_ID);
			prof.setPassword(password);
			prof.setAddress(address);
			prof.setDepartment(department);
			prof.setDesignation(designation);
			prof.setDOJ(doj);

			return prof;
		}
		return null;
	}
}
