package com.signify.service;

import com.signify.bean.Course;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.bean.User;
import com.signify.collection.CourseData;
import com.signify.collection.StudentCourse;
import com.signify.collection.StudentRegistered;
import com.signify.collection.UserSession;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class StudentService extends UserService implements StudentInterface {
	@Override
	public void viewGrades() {
		System.out.println("Viewing Grades...");
		HashSet<RegisteredCourse> courses = StudentCourse.regCourses.getOrDefault(UserSession.user.getUsername() , null);
		if(courses == null) {
			System.out.println("No Courses Registered");
			return;
		}
		
		for(RegisteredCourse crs : courses) {
			System.out.print("Course Name : " + crs.getName() + "\nCourse Grade : " + crs.getGrade() + "\n");
		}
	}
	@Override
	public void viewRegisterCourses() {
		System.out.println("Viewing Registered Course...");
		HashSet<RegisteredCourse> courses = StudentCourse.regCourses.getOrDefault(UserSession.user.getUsername() , null);
		if(courses == null) {
			System.out.println("No Course Registered");
			return;
		}
		for(RegisteredCourse crs : courses) {
			System.out.print("Course Code : " + crs.getCourseCode() + "\nCourse Name : " + crs.getName() + "\nCourse Grade : " + crs.getGrade() + "\n");
		}
	}
	@Override
	public void addCourse(String courseId , String semester) {
		Course crs = CourseData.courses.getOrDefault(courseId, null);
		if(crs == null) {
			System.out.println("This Course is not provided : \n");
			return;
		}
		User usr = UserSession.user;
		if(StudentRegistered.regStudents.containsKey(courseId)) {
			StudentRegistered.regStudents.get(courseId).add((Student)usr);			
		}
		else {
			HashSet<Student> set = new HashSet<Student>(); 
			set.add((Student)usr);
			StudentRegistered.regStudents.put(courseId, set);
		}
		// Handle Student registration for a course;
		RegisteredCourse regCourse = new RegisteredCourse();
		regCourse.setCourseCode(crs.getCourseCode());
		regCourse.setStudentId(usr.getUserId());
		regCourse.setGrade("Ungraded");
		if(StudentCourse.regCourses.containsKey(usr.getUsername())) {
			StudentCourse.regCourses.get(usr.getUsername()).add(regCourse);
		}
		else {
			HashSet<RegisteredCourse> courses = new HashSet<RegisteredCourse>();
			courses.add(regCourse);
			StudentCourse.regCourses.put(usr.getUsername() , courses);
		}
		System.out.println("Student Registered for the Course");
	}
	@Override
	public void dropCourse(String courseId) {
		User usr = UserSession.user;
		StudentRegistered.regStudents.get(courseId).remove(usr);
		StudentCourse.regCourses.get(UserSession.user.getUsername()).removeIf(crs -> crs.getCourseCode().equals(courseId));
		System.out.println("Student Dropped Course , Course ID -> " + courseId);	
	}
	@Override
	public void payFees() {
		
		
	}
	@Override
	public void viewGrade(String courseId) {
		HashSet<RegisteredCourse> it =  StudentCourse.regCourses.getOrDefault(UserSession.user.getUsername() , null);
		
		if(it == null) {
			System.out.println("No Course Registered with this ID : ");
			return;
		}
		
		while(it.iterator().hasNext()) {
			if(it.iterator().next().getCourseCode().equals(courseId)) {
				System.out.println("Viewing Course Grades , Course Id -> " + it.iterator().next().getGrade());
				return;
			}
		}		
	}
}
