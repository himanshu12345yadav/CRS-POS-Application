package com.signify.collection;

import java.util.HashMap;
import java.util.HashSet;

import com.signify.bean.RegisteredCourse;

public class StudentCourse {
	// Registered Course for a Student
	public static HashMap<String , HashSet<RegisteredCourse>> regCourses = new HashMap<String , HashSet<RegisteredCourse>>();
}
