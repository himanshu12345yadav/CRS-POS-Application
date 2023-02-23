/**
 * 
 */
package com.signify.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

import com.mysql.cj.xdevapi.DbDoc;
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

		Student student = new Student(userid, studentid, name, password, address, branch, doj, age, bg, fName, phNum,
				0);

		return student;
	}

	public User registerUser(int roleid) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Name : ");
		String name = sc.next();
		System.out.print("Enter Password : ");
		String password = sc.next();
		System.out.print("Enter Address : ");
		String address = sc.next();
		System.out.print("Enter Date of joining : ");
		String doj = sc.next();
		String userid = UUID.randomUUID().toString();

		if (roleid == DBConstants.ADMIN_ROLE_ID) {
			Admin admin = new Admin(userid, name);
			admin.setRoleid(DBConstants.ADMIN_ROLE_ID);
			admin.setPassword(password);
			admin.setAddress(address);

			return admin;
		} else if (roleid == DBConstants.PROFESSOR_ROLE_ID) {
			System.out.println("Enter Department");
			String department = sc.nextLine();
			System.out.println("Enter Designation");
			String designation = sc.nextLine();

			Professor prof = new Professor(userid, name);
			prof.setRoleid(DBConstants.ADMIN_ROLE_ID);
			prof.setPassword(password);
			prof.setAddress(address);
			prof.setDepartment(department);
			prof.setDesignation(designation);

			return prof;
		}
		return null;
	}
}
