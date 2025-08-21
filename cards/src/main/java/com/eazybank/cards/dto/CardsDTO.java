package com.eazybank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
@Schema(name = "Cards",
description = "Schema to hold Card information"
)
public class CardsDTO {

	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	 @Schema(
	            description = "Mobile Number of Customer", example = "9848208509"
	    )
	private String mobileNumber;
	@NotEmpty(message = "card number cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{12})",message = " cardnumber must be 10 digits")
	@Schema(
            description = "Card Number of the customer", example = "100646930341"
    )
	private String cardNumber;
	@NotEmpty(message = "CardType cannot be a null or empty")
	 @Schema(
	            description = "Type of the card", example = "Credit Card"
	    )
	private String cardType;
	@Positive(message = "Total card limit should be greater than zero")
	 @Schema(
	            description = "Total amount limit available against a card", example = "100000"
	    )
	private int totalLimit;
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	 @Schema(
	            description = "Total amount used by a Customer", example = "1000"
	    )
	private int amountUsed;
	@PositiveOrZero(message = "Total amount used should be equal or greater than zero")
	@Schema(
            description = "Total available amount against a card", example = "90000"
    )
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
