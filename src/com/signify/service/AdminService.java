package com.signify.service;

import com.signify.bean.Course;
import com.signify.collection.CourseData;

public class AdminService extends UserService implements AdminInterface {
	@Override
	public void generateReportCard(String studentId , String courseId , int semester) {
		System.out.println("Report card generated");
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
	public void approveStudent(String studentId) {
		System.out.println("Student with Student ID approved -> " + studentId);
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
