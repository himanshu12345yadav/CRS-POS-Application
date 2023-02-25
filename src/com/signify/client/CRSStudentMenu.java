package com.signify.client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.signify.bean.Course;
import com.signify.bean.OfflinePayment;
import com.signify.bean.OnlinePayment;
import com.signify.bean.Payment;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.collection.UserSession;
import com.signify.exception.CourseNotFoundException;
import com.signify.exception.SemesterNotRegisteredException;
import com.signify.exception.StudentNotRegisteredException;
import com.signify.reader.StudentPayment;
import com.signify.service.StudentService;

public class CRSStudentMenu {
	private String prompt = "\nSTUDENT MENU\n--------------\n1. SEMESTER REGISTRATION\n" + "2. VIEW REPORT CARD\n"
			+ "3. VIEW REGISTERED COURSE\n" + "4. ADD COURSE\n" + "5. DROP COURSE\n" + "6. PAY FEES\n" + "7. LOGOUT";

	public void displayStudentMenu() {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		StudentService svc = new StudentService();
		int input = 0;

		Student usr = (Student) UserSession.getUser();
		String studentId = usr.getStudentId();

		do {
			System.out.println(prompt);
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			}

			switch (input) {
				case 1:
					System.out.print("Enter Semester : ");
					int semester = Integer.parseInt(sc.nextLine());
					try {
						svc.semesterRegister(studentId, semester);
					} catch (StudentNotRegisteredException e) {
						System.err.println("[error] : Student Not Registered");
					}
					break;
				case 2:
					List<RegisteredCourse> courses = svc.getGrades(studentId);
					// ! ADD CPI AT THE END OF THE GRADES
					if (courses == null) {
						System.out.printf("%n[info] : No Course Found%n");
						break;
					} else {
						System.out.printf("----------------%nReport Card------------%n");
						System.out.printf("|Course Code%-50s|Course Name %-50s| Course Grade %-50s|%n");

						for (RegisteredCourse course : courses) {
							System.out.printf("|%-50s|%-50s|%-50s%n", course.getCourseCode(), course.getCourseName(),
									course.getGrade());
						}

						System.out.printf("----------------------------%n");
					}
				case 3:
					List<RegisteredCourse> regCourses = svc.getRegisteredCourses(studentId);

					if (regCourses == null) {
						System.out.println("You have not registered for any course");
					} else {
						System.out.printf("----------------%Registered Course------------%n");
						System.out.printf("|Course Code\t\t\t|Course Name\t\t\t|Semester%n");

						for (RegisteredCourse course : regCourses) {
							System.out.printf("|%-50s|%-50s|%-50s|%-50s|%n", course.getCourseCode(),
									course.getCourseName(),
									course.getSemester(), course.getType());
						}
					}
				case 4:
					// ! ADD MAXIMUM SEATS LIMIT TO A COURSE
					List<Course> availableCourse = null;
					try {
						availableCourse = svc.getAvailableCourses(studentId);
					} catch (SemesterNotRegisteredException e) {
						System.out.println("[error] : Semester Not Registered");
						break;
					}

					System.out.printf("----------------%nReport Card------------%n");
					System.out.printf("|Course Code%-50s|Course Name %-50s| Course Grade %-50s|%n");

					for (Course course : availableCourse) {
						System.out.printf("|%-50s|%-50s|%-50s%n", course.getCourseCode(), course.getCourseName(),
								course.getInstructor());
					}

					System.out.printf("----------------------------%n");
				case 5: {
				}
					List<RegisteredCourse> rc = svc.getRegisteredCourses(studentId);

					if (rc.size() == 0) {
						System.err.println("%n[error]: You haven't registered for any course%n");
						break;
					}

					System.out.printf("----------------%nReport Card------------%n");
					System.out.printf("|Course Code%-50s|Course Name %-50s| Course Grade %-50s|%n");
					for (RegisteredCourse course : rc) {
						System.out.printf("|Course Code%-50s|Course Name %-50s| Semester %-50s|%n",
								course.getCourseCode(), course.getCourseName(), course.getSemester());
					}

					System.out.printf("%nEnter Course Code%n");
					String courseCode = sc.nextLine();
					try {
						svc.dropCourse(studentId, courseCode);
						System.out.printf("%nCourse Dropped Successfully%n");
					} catch (CourseNotFoundException e) {
						System.out.println(e.getMessage());
					}
				case 6:
					List<Course> coursesfees = svc.getFees(studentId);
					double totalFee = 0;
					for (Course course : coursesfees) {
						totalFee += course.getFee();
					}
					System.out.printf("%n Total Fee to be paid %f %n", totalFee);

					StudentPayment studentPayment = new StudentPayment();
					Payment payment = studentPayment.requestPayment(totalFee);

					if (payment instanceof OnlinePayment) {
						svc.payFeesOnline((OnlinePayment) payment);
					} else if (payment instanceof OfflinePayment) {
						svc.payFeesOffline((OfflinePayment) payment);
					}
				default:
					break;
			}

		} while (flag);
	}
}
