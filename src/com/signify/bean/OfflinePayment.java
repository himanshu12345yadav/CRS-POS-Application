package com.signify.bean;

public class OfflinePayment extends Payment {
	private int isCash;
	private int chequeNumber;
	private String bankName;

	public OfflinePayment(int isCash, int chequeNumber, String bankName) {
		this.isCash = isCash;
		this.chequeNumber = chequeNumber;
		this.bankName = bankName;
	}

	public int isCash() {
		return isCash;
	}

	public void setCash(int isCash) {
		this.isCash = isCash;
	}

	public int getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(int chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
