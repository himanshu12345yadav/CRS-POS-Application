package com.signify.client;

import java.util.List;
import java.util.Scanner;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.collection.UserSession;
import com.signify.constants.DBConstants;
import com.signify.exception.CourseNotAssignedException;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.UserAlreadyExistsException;
import com.signify.exception.UserNotFoundException;
import com.signify.reader.UserRegistration;
import com.signify.service.AdminService;

public class CRSAdminMenu {
	private String prompt = "\nADMIN MENU\n------------\n1. APPROVE STUDENT \n" + "2. ADD PROFESSOR \n"
			+ "3. ADD NEW ADMIN\n" + "4. ASSIGN PROFESSOR TO COURSE\n" + "5. ADD COURSE \n" + "6. REMOVE COURSE\n"
			+ "7. VIEW COURSE DETAILS\n" + "8. CALCULATE CPI\n" + "9. VIEW PROFESSORS\n" + "10. VIEW ADMINS\n"
			+ "11. LOGOUT";

	public void displayAdminMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		AdminService svc = new AdminService();

		do {
			System.out.println(prompt);
			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
				case 1:
					List<Student> students = svc.getUnapprovedStudents();
					if (students.size() == 0) {
						System.err.println("[info] : No Unapproved Students");
						break;
					}
					System.out.println("List of Unapproved Students");

					for (Student i : students) {
						System.out.printf("%s %-10s %s", i.getStudentId(), i.getStudentName(), i.getBranch());
					}

					System.out.printf("%n1. To Approve all students %n2. To Approve by Id %n");
					int choice = Integer.parseInt(sc.nextLine());
					switch (choice) {
						case 1:
							svc.approveAllStudents();
							System.out.println("\n[info] : All Student Approved");
							break;
						case 2:
							System.out.print("Enter Student id : ");
							String studentId = sc.nextLine();
							try {
								svc.approveStudentById(studentId);
								System.out.println("\n[info] : Student Approved");
							} catch (UserNotFoundException e) {
								System.err.println(e.getMessage());
							}
							break;
						default:
							System.out.println("Invalid Input Received");
							break;
					}
					break;
				case 2:
					UserRegistration profUser = new UserRegistration();
					Professor prof = (Professor) profUser.registerUser(DBConstants.PROFESSOR_ROLE_ID);
					try {
						svc.addProfessor(prof);
						System.out.println("\n[info] : Professor added successfully");
					} catch (UserAlreadyExistsException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3: {
					UserRegistration adminUser = new UserRegistration();
					Admin admin = (Admin) adminUser.registerUser(DBConstants.ADMIN_ROLE_ID);
					try {
						svc.addAdmin(admin);
						System.out.println("\n[info] : Admin Registered Successfully");
					} catch (UserAlreadyExistsException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 4: {
					System.out.print("Enter Professor Id : ");
					String professorId = sc.nextLine();
					System.out.print("Enter Course Code : ");
					String courseCode = sc.nextLine();
					try {
						svc.assignProfessorToCourse(professorId, courseCode);
						System.out.println("\n[info] : Course assigned successfully");
					} catch (CourseNotAssignedException e) {
						System.err.println(e.getMessage());
					}
					break;
				}
				case 5: {
					System.out.println("Enter Course Code : ");
					String courseCode = sc.nextLine();
					System.out.println("Enter Course Name : ");
					String courseName = sc.nextLine();
					System.out.println("Enter Course Instructor : ");
					String instructor = sc.nextLine();
					System.out.println("Enter Course Fee :");
					Double coursefee = sc.nextDouble();

					// TODO : CHECK COURSE SEMESTER IN ADMIN MENU
					// System.out.println("Enter Semester : ");
					// int semester = Integer.parseInt(sc.nextLine());

					Course course = new Course(courseCode, courseName, instructor, coursefee);
					try {
						svc.addCourse(course);
					} catch (CourseNotAssignedException e) {
						System.err.println(e.getMessage());
					}

					break;
				}
				case 6: {
					System.out.println("Enter Course Code : ");
					String courseCode = sc.nextLine();
					try {
						svc.removeCourse(courseCode);
					} catch (CourseNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 7:
					System.out.println("Enter Course Code");
					String courseCode = sc.nextLine();
					Course course = svc.getCourseDetails(courseCode);

					if (course != null) {
						System.out.println("\nCourse Code\tCourse Name\tInstructor\tCourse Fee\tSemester");
						System.out.printf("%s\t%s\t%s\t%f\t%s", course.getCourseCode(), course.getCourseName(),
								course.getInstructor(), course.getFee(), course.getCourseName());
					}
				case 8: {
					System.out.println("Enter Student ID : ");
					String studentId = sc.nextLine();
					svc.calculateCpi(studentId);
				}
				case 9:
					List<Professor> professors;

					professors = svc.getProfessors();

					if (professors.size() == 0) {
						System.err.println("No Professors found");
						break;
					}
					System.out.printf(
							"%nProfessor Id\tProfessor Name\tDepartment\tDesignation\tCourse taught%n");
					for (Professor professor : professors) {
						System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", professor.getUserId(),
								professor.getUsername(),
								professor.getDepartment(), professor.getDesignation(), professor.getCourse());
					}
					break;
				case 10:
					List<Admin> admins = svc.getAdmins();
					if (admins.size() == 0) {
						System.err.println("No Admins Found");
						break;
					}
					System.out.printf("Admin Id \t\t\t Admin Name %n");
					for (Admin admin : admins) {
						System.out.printf("|%-30s| %-30s|%n", admin.getUserId(), admin.getUsername());
					}
					break;
				case 11:
					UserSession.clearSession();
					return;
				default:
					System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}
}
