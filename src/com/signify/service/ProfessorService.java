package com.signify.service;

import java.util.HashSet;
import java.util.Iterator;

import com.signify.bean.RegisteredCourse;
import com.signify.bean.Student;
import com.signify.collection.StudentCourse;
import com.signify.collection.StudentRegistered;

public class ProfessorService extends UserService implements ProfessorInterface {
	@Override
	public void viewEnrolledStudents(String courseId) {
		System.out.println("View enrolled Students");
		HashSet<Student> students = StudentRegistered.regStudents.getOrDefault(courseId , null);
		if(students == null) {
			System.out.println("No Enrolled Students for this Course");
		}
		System.out.println("Enrolled Students are : ");
		for(Student i : students) {
			System.out.println(i.getName());
		}
	}
	@Override
	public void addGrade(String studentId , String courseCode , String grade) {
		System.out.println("Added grades");
		HashSet<RegisteredCourse> course = StudentCourse.regCourses.getOrDefault(studentId , null);
		
		if(course == null) {
			System.out.println("Wrong Student Id ");
			return;
		}
		
		Iterator<RegisteredCourse> crs = course.iterator();
		
		while(crs.hasNext()) {
			if(crs.next().getCourseCode().equals(courseCode)) {
				crs.next().setGrade(grade);
				System.out.println("Student Graded");
				return;
			}
		}
		
		System.out.println("Student Not Registered for this Course");
	}
}
