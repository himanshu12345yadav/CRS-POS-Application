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
import com.signify.exception.CourseLimitExceedException;
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
				input = Integer.parseInt(sc.nextLine());
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
			}

			switch (input) {
				case 1:
					System.out.print("Enter Semester : ");
					int semester = Integer.parseInt(sc.nextLine());
					try {
						svc.semesterRegister(studentId, semester);
						System.out.println("\n[info] : Semester Registered Successfully");
					} catch (StudentNotRegisteredException e) {
						System.err.println("\n[error] : Student Not Registered");
					}
					break;
				case 2:
					List<RegisteredCourse> courses = svc.getGrades(studentId);
					// ! ADD CPI AT THE END OF THE GRADES
					if (courses.size() == 0) {
						System.out.printf("%n[info] : No Course Found%n");
						break;
					} else {
						System.out.printf("----------------%nReport Card------------%n");
						System.out.printf("|Course Code|Course Name| Course Grade|%n");

						for (RegisteredCourse course : courses) {
							System.out.printf("|%-50s|%-50s|%-50s%n", course.getCourseCode(), course.getCourseName(),
									course.getGrade());
						}

						System.out.printf("----------------------------%n");
					}
					break;
				case 3:
					List<RegisteredCourse> regCourses = svc.getRegisteredCourses(studentId);

					if (regCourses.size() == 0) {
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
					break;
				case 4:
					// ! ADD MAXIMUM SEATS LIMIT TO A COURSE
					List<Course> availableCourse = null;
					try {
						availableCourse = svc.getAvailableCourses(studentId);
					} catch (SemesterNotRegisteredException e) {
						System.out.println("[error] : Semester Not Registered");
						break;
					}

					System.out.printf("----------------%nAvailable Courses------------%n");
					System.out.printf("|Course Code|Course Name| Course Instructor|%n");

					for (Course course : availableCourse) {
						System.out.printf("|%-10s|%-10s|%-10s%n", course.getCourseCode(), course.getCourseName(),
								course.getInstructor());
					}
					System.out.printf("----------------------------%n");

					System.out.print("Enter Course code : ");
					String coursecode = sc.nextLine();
					System.out.print("Enter Course type [1/2] : ");
					int coursetype = Integer.parseInt(sc.nextLine());

					try {
						svc.addCourse(studentId, coursecode, coursetype);
						System.out.println("\n[info] : Registered Successfully for the course");
					} catch (CourseNotFoundException | CourseLimitExceedException e) {
						System.err.println(e.getMessage());
					}

					break;
				case 5: {
				}
					List<RegisteredCourse> rc = svc.getRegisteredCourses(studentId);

					if (rc.size() == 0) {
						System.err.println("%n[error]: You haven't registered for any course%n");
						break;
					}

					System.out.printf("----------------Registered Courses------------%n");
					System.out.printf("|Course Code|Course Name| Course Grade|%n");
					for (RegisteredCourse course : rc) {
						System.out.printf("|%-10s|%-10s|%-10s|%n",
								course.getCourseCode(), course.getCourseName(), course.getSemester());
					}

					System.out.print("%nEnter Course Code :");
					String courseCode = sc.nextLine();
					try {
						svc.dropCourse(studentId, courseCode);
						System.out.printf("%nCourse Dropped Successfully%n");
					} catch (CourseNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					List<Course> coursesfees = svc.getFees(studentId);
					double totalFee = 0;
					for (Course course : coursesfees) {
						totalFee += course.getFee();
					}
					System.out.printf("%nTotal Fee to be paid %f %n", totalFee);

					StudentPayment studentPayment = new StudentPayment();
					Payment payment = studentPayment.requestPayment(totalFee);

					if (payment instanceof OnlinePayment) {
						svc.payFeesOnline((OnlinePayment) payment);
					} else if (payment instanceof OfflinePayment) {
						svc.payFeesOffline((OfflinePayment) payment);
					}

					System.out.println("Fee Paid Successfully");
					break;
				case 7:
					UserSession.clearSession();
					return;
				default:
					break;
			}

		} while (flag);
	}
}
