package com.signify.bean;

public class Scholarship {
	private String scholarshipName;

	public String getScholarshipName() {
		return scholarshipName;
	}

	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}

	public Scholarship(String scholarshipName) {
		super();
		this.scholarshipName = scholarshipName;
	}
	
}
