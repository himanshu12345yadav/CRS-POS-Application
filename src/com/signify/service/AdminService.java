package com.signify.service;

import java.util.HashSet;
import java.util.Iterator;

import com.signify.bean.Course;
import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.collection.CourseData;
import com.signify.collection.StudentCourse;
import com.signify.collection.UserData;

public class AdminService extends UserService implements AdminInterface {
	@Override
	public void generateReportCard(String studentId , String courseId , int semester) {
		System.out.println("Added grades");
		HashSet<RegisteredCourse> course = StudentCourse.regCourses.getOrDefault(studentId , null);
		
		if(course == null) {
			System.out.println("Wrong Student Id ");
			return;
		}
		System.out.println("Student Report Card");
		Iterator<RegisteredCourse> crs = course.iterator();
		
		while(crs.hasNext()) {
			RegisteredCourse reportcard = crs.next();
			System.out.println("Course ID : " + reportcard.getName() + "\nCourse Code" + reportcard.getCourseCode() + "\nCourse Grades: " + reportcard.getGrade());
		}
	}
	@Override
	public void addCourse(String courseCode , String name , boolean isOffered , String instructor) {
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setName(name);
		course.setOffered(isOffered);
		course.setInstructor(instructor);
		CourseData.courses.put(courseCode, course);
	}
	@Override
	public void removeCourse(String courseId) {
		if(CourseData.courses.containsKey(courseId)) {
			CourseData.courses.remove(courseId);
			System.out.println("Course Removed");
		}
		else {
			System.out.println("No Course Found");
		}
	}
	@Override
	public void approveStudent(String studentUsername) {
		Student usr = (Student)UserData.users.getOrDefault(studentUsername , null);
		
		if(usr == null) {
			System.out.println("User Not Registered");
			return;
		}
		usr.setApproved(true);
		System.out.println("Student Approved for Login");
	}
	@Override
	public void addProfessor(String professorId) {
		System.out.println("Professor is added");
	}
	@Override
	public void assignProfessorToCourse(String professorId, String courseId) {
		Course crs = CourseData.courses.getOrDefault(courseId , null);
		
		if(crs == null) {
			System.out.println("No Course with this Course ID");
		}
		
		crs.setInstructor(professorId);
		System.out.println("Professor with " + professorId + " is assigned to the Course " + courseId);
	}
	@Override
	public void viewCourseDetails(String courseId) {
		if(CourseData.courses.containsKey(courseId)) {
			Course course = CourseData.courses.get(courseId);
			System.out.println("CourseId :  " + course.getCourseCode() + "\n" + "Course Name : " + course.getName() + "\n" + "Course Offered : " + course.isOffered() + "\n" + "Course Instructor : " + course.getInstructor());
		}
		else {
			System.out.println("Course Not Found");
		}
	}
	@Override
	public double calculateCpi(String studentId) {
		System.out.println("Calculating the cpi");
		return 0;
	}
}
