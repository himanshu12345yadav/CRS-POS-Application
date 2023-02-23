package com.signify.client;

import java.util.List;
import java.util.Scanner;

import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Student;
import com.signify.collection.UserSession;
import com.signify.constants.DBConstants;
import com.signify.reader.UserRegistration;
import com.signify.service.AdminService;
import com.signify.service.UserService;

public class CRSAdminMenu {
	private String prompt = "\nADMIN MENU\n------------\n1. -> FOR APPROVING STUDENT \n" + "2. -> TO ADD PROFESSOR \n"
			+ "3. -> TO ADD NEW ADMIN\n" + "4. -> TO GENERATE REPORT CARD \n" + "5. -> TO ASSIGN A PROFESSOR\n"
			+ "6. -> TO ADD COURSE \n" + "7. -> TO REMOVE COURSE\n" + "8. -> TO VIEW COURSES\n" + "9. -> LOGOUT\n";

	public void displayAdminMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		AdminService as = new AdminService();

		do {
			System.out.println(prompt);
			int input = sc.nextInt();

			switch (input) {
			case 1:
				System.out.println("List of Unapproved Students");
				List<Student> students = as.getUnapprovedStudents();

				for (Student i : students) {
					System.out.printf("%s %-10s %s", i.getUserId(), i.getUsername(), i.getName());
				}
				System.out.printf("1. To Approve all students %n 2. To Approve by Id %n");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					as.approveAllStudents();
					break;
				case 2:
					System.out.print("Enter Student id : ");
					String studentId = sc.nextLine();
					as.approveStudentById(studentId);
					break;
				default:
					System.out.println("Invalid Input Received");
					break;
				}
				break;
			case 2:
				UserRegistration profUser = new UserRegistration();
				Professor prof = (Professor) profUser.registerUser(DBConstants.PROFESSOR_ROLE_ID);
				as.addProfessor(prof);
				break;
			case 3:
				UserRegistration adminUser = new UserRegistration();
				Admin admin = (Admin) adminUser.registerUser(DBConstants.ADMIN_ROLE_ID);
				as.addAdmin(admin);
				break;
			case 4:
				System.out.print("Enter Student Id : ");
				String stuId = sc.nextLine();
				as.generateReportCard(stuId);
				break;
			case 5:
				System.out.println("Professor Id : ");
				String courseProfId = sc.nextLine();
				System.out.println("Course Id : ");
				String CourseId = sc.nextLine();
				as.assignProfessorToCourse(CourseId, courseProfId);
				break;
			case 6:
				System.out.print("Course Code :");
				String courseCode = sc.nextLine();
				System.out.print("Course Name : ");
				String courseName = sc.nextLine();
				System.out.print("Course Instructor");
				String instructor = sc.nextLine();
				System.out.print("Course Fee");
				Double fee = Double.parseDouble(sc.nextLine());

				Course course = new Course(courseCode, courseName, instructor, fee);
				as.addCourse(course);
				break;
			case 7:
				System.out.print("Course Code : ");
				String removeCourseCode = sc.nextLine();
				as.removeCourse(removeCourseCode);
				break;
			case 8:
				System.out.println("Course Code : ");
				String courseCodeDetails = sc.nextLine();
				Course crs = as.getCourseDetails(courseCodeDetails);
				System.out.printf("Course Name %-5s %s Course Code %-5s %s Instructor %-5s %s Fee %-5s %f", crs.getName() , crs.getCourseCode() , crs.getInstructor() , crs.getCoursefee());
				break;
			case 9:
				UserSession.user = null;
				return;
			default:
				System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}
}
