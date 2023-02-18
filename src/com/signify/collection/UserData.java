package com.signify.collection;

import com.signify.bean.Student;
import com.signify.bean.Admin;
import com.signify.bean.Course;
import com.signify.bean.Professor;
import com.signify.bean.Payment;

import java.util.HashMap;

public class UserData {
	public static HashMap<Integer , Student> users = new HashMap<Integer , Student>();
	public static HashMap<Integer , Admin> admins = new HashMap<Integer , Admin>();
	public static HashMap<Integer , Professor> prof = new HashMap<Integer , Professor>();
	public static HashMap<Integer , Course> courses = new HashMap<Integer , Course>();
	public static HashMap<Integer , Payment> payments = new HashMap<Integer , Payment>();
}
