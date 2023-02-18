package com.signify.collection;

import com.signify.bean.User;
import com.signify.bean.Course;
import com.signify.bean.Payment;

import java.util.HashMap;

public class UserData {
	public static HashMap<String , User> users = new HashMap<String , User>();
	public static HashMap<String , Course> courses = new HashMap<String , Course>();
	public static HashMap<String, Payment> payments = new HashMap<String , Payment>();
}
