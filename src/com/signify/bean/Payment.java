package com.signify.bean;

public class Payment {
	private int studentId;
	private String referenceId;
	private float amount;
	private boolean status;
	private boolean isOnline;
	public boolean isOnline() {
		return isOnline;
	}
	public Payment(int studentId, String referenceId, float amount, boolean status, boolean isOnline) {
		super();
		this.studentId = studentId;
		this.referenceId = referenceId;
		this.amount = amount;
		this.status = status;
		this.isOnline = isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}	
}
