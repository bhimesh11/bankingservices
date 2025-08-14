package com.eazybank.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerDTO {

	


private String name;
private String email;
private String mobileNumber;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public CustomerDTO(String name, String email, String mobileNumber) {
	super();
	this.name = name;
	this.email = email;
	this.mobileNumber = mobileNumber;
}
public CustomerDTO() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "CustomerDTO [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + "]";
}





}
