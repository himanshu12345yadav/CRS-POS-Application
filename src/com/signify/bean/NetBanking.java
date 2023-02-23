package com.signify.bean;

public class NetBanking extends Payment {
	public NetBanking(int studentId, String referenceId, float amount, boolean status, boolean isOnline,
			String modeOfTransfer, String iFSCcode, int accountNumber) {
		super(studentId, referenceId, amount, status, isOnline);
		this.modeOfTransfer = modeOfTransfer;
		IFSCcode = iFSCcode;
		this.accountNumber = accountNumber;
	}
	private String modeOfTransfer , IFSCcode;
	private int accountNumber;
	public String getModeOfTransfer() {
		return modeOfTransfer;
	}
	public void setModeOfTransfer(String modeOfTransfer) {
		this.modeOfTransfer = modeOfTransfer;
	}
	public String getIFSCcode() {
		return IFSCcode;
	}
	public void setIFSCcode(String iFSCcode) {
		IFSCcode = iFSCcode;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
