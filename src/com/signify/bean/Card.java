package com.signify.bean;

public class Card extends Payment {
	private int cardNumber;
	private String cardType;
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public Card(int studentId, String referenceId, float amount, boolean status, boolean isOnline, int cardNumber,
			String cardType) {
		super(studentId, referenceId, amount, status, isOnline);
		this.cardNumber = cardNumber;
		this.cardType = cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
