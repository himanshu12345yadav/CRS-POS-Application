package com.signify.client;

import java.util.Map;
import java.util.Scanner;

import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.collection.UserData;
import com.signify.collection.UserSession;
import com.signify.service.AdminService;
import com.signify.service.UserService;

public class CRSAdminMenu {
	private String prompt = "\nADMIN MENU\n------------\nPRESS 1 -> FOR APPROVING STUDENT \n"
			+ "PRESS 2 -> TO ADD PROFESSOR \n"
			+ "PRESS 3 -> TO GENERATE REPORT CARD \n"
			+ "PRESS 4 -> TO ASSIGN A PROFESSOR\n"
			+ "PRESS 5 -> TO ADD COURSE \n"
			+ "PRESS 6 -> TO REMOVE COURSE\n"
			+ "PRESS 7 -> TO VIEW COURSES\n"
			+ "PRESS 8 -> LOGOUT\n";
	
	public void displayAdminMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		AdminService as = new AdminService();
		
		do {
			System.out.println(prompt);
			int input = sc.nextInt();
			
			switch(input) {
			case 1 :
				System.out.println("List of Unapproved Students");
				for(Map.Entry<String , User> i : UserData.users.entrySet()) {
					if(i.getValue() instanceof Student && ((Student) i.getValue()).isApproved() == false) {
						System.out.println("Student Id : " + i.getValue().getUsername() + "\tStudent Name : " + i.getValue().getName());
					}
				}
				System.out.print("Enter Student Id : ");
				String studentId = sc.next();
				as.approveStudent(studentId);
				break;
			case 2 :
				System.out.print("ENTER USERNAME :");
				String username = sc.next();
				System.out.print("ENTER PASSWORD :");
				String password = sc.next();
				System.out.print("ENTER NAME :");
				String name = sc.next();
				System.out.print("ENTER ADDRESS :");
				String address = sc.next();
				as.addProfessor(username, password, name, address);
				break;
			case 3 :
				System.out.print("Enter Student Id : ");
				String stuId = sc.next();
				System.out.println("Enter Course Id : ");
				String courseId = sc.next();
				System.out.println("Enter Semester : ");
				Integer semester = sc.nextInt();
				as.generateReportCard(stuId, courseId, semester);
				break;
			case 4 :
				System.out.println("Professor Id : ");
				String courseProfId = sc.next();
				System.out.println("Course Id : ");
				String CourseId = sc.next();
				as.assignProfessorToCourse(CourseId , courseProfId);
				break;
			case 5 :
				System.out.print("Course Code :");
				String courseCode = sc.next();
				System.out.print("Name : ");
				String courseName = sc.next();
				System.out.print("isOffered : ");
				boolean isOffered = sc.nextBoolean();
				System.out.print("Intructor : ");
				String instructor = sc.next();
				as.addCourse(courseCode , courseName , isOffered , instructor);
				break;
			case 6 :
				System.out.print("Course Code : ");
				String removeCourseCode = sc.next();
				as.removeCourse(removeCourseCode);
				break;
			case 7 : 
				System.out.println("Course Code : ");
				String courseCodeDetails = sc.next();
				as.viewCourseDetails(courseCodeDetails);
				break;
			case 8:
				UserSession.user = null;
				return;
			default:
				System.out.println("Wrong input : Try again");
			}
		} while (flag);
	}	
}
