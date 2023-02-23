package com.signify.bean;

public class Student extends User {
	private int age , isApproved;
	private String studentId , branch, batch, name, address, doj, bloodgroup, fName, phNum;


	public Student() {
		super();
	}

	public Student(String userId, String username) {
		super(userId, username);
	}

	public Student(String userId, String username, String name, String password, String address, String branch,
			String doj, int age, String bg, String fName, String phNum, int isApproved) {
		super(userId, username);
		this.name = name;
		this.address = address;
		this.branch = branch;
		this.doj = doj;
		this.setAge(age);
		this.bloodgroup = bg;
		this.fName = fName;
		this.phNum = phNum;
		this.isApproved = isApproved;
		super.setPassword(password);
	}

	public int isApproved() {
		return isApproved;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getBloodGroup() {
		return bloodgroup;
	}

	public void setBloodGroup(String bg) {
		this.bloodgroup = bg;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public void setApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
