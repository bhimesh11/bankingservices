package com.eazybank.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CardsDTO {

	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	@NotEmpty(message = "card number cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{12})",message = " cardnumber must be 10 digits")
	private String cardNumber;
	@NotEmpty(message = "CardType cannot be a null or empty")
	private String cardType;
	@Positive(message = "Total card limit should be greater than zero")
	private int totalLimit;
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	private int amountUsed;
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	private int availableAmount;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getTotalLimit() {
		return totalLimit;
	}
	public void setTotalLimit(int totalLimit) {
		this.totalLimit = totalLimit;
	}
	public int getAmountUsed() {
		return amountUsed;
	}
	public void setAmountUsed(int amountUsed) {
		this.amountUsed = amountUsed;
	}
	public int getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}
	@Override
	public String toString() {
		return "CardsDTO [mobileNumber=" + mobileNumber + ", cardNumber=" + cardNumber + ", cardType=" + cardType
				+ ", totalLimit=" + totalLimit + ", amountUsed=" + amountUsed + ", availableAmount=" + availableAmount
				+ "]";
	}
	public CardsDTO(String mobileNumber, String cardNumber, String cardType, int totalLimit, int amountUsed,
			int availableAmount) {
		super();
		this.mobileNumber = mobileNumber;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.totalLimit = totalLimit;
		this.amountUsed = amountUsed;
		this.availableAmount = availableAmount;
	}
	public CardsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
