package com.signify.bean;

public class Cheque extends Payment {
	private String bankName;
	private int chequeNumber;
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Cheque(int studentId, String referenceId, float amount, boolean status, boolean isOnline, String bankName,
			int chequeNumber) {
		super(studentId, referenceId, amount, status, isOnline);
		this.bankName = bankName;
		this.chequeNumber = chequeNumber;
	}
	public int getChequeNumber() {
		return chequeNumber;
	}
	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}
}
