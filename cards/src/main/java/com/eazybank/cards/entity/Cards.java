package com.eazybank.cards.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cards extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long carId;
private String mobileNumber;
private String cardNumber;
private String cardType;
private int totalLimit;
private int amountUsed;
private int availableAmount;
public Long getCarId() {
	return carId;
}
public void setCarId(Long carId) {
	this.carId = carId;
}
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
	return "Cards [carId=" + carId + ", mobileNumber=" + mobileNumber + ", cardNumber=" + cardNumber + ", cardType="
			+ cardType + ", totalLimit=" + totalLimit + ", amountUsed=" + amountUsed + ", availableAmount="
			+ availableAmount + "]";
}
public Cards(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, Long carId,
		String mobileNumber, String cardNumber, String cardType, int totalLimit, int amountUsed, int availableAmount) {
	super(createdAt, createdBy, updatedAt, updatedBy);
	this.carId = carId;
	this.mobileNumber = mobileNumber;
	this.cardNumber = cardNumber;
	this.cardType = cardType;
	this.totalLimit = totalLimit;
	this.amountUsed = amountUsed;
	this.availableAmount = availableAmount;
}
public Cards() {
	super();
	// TODO Auto-generated constructor stub
}
public Cards(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {
	super(createdAt, createdBy, updatedAt, updatedBy);
	// TODO Auto-generated constructor stub
}


}
