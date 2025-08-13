package com.eazybank.accounts.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class customer extends BaseEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "customer_id")
private Long customerId;
private String name;
private String email;
@Column(name = "mobile_number")
private String mobileNumber;
public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
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
public customer(Long customerId, String name, String email, String mobileNumber) {
	super();
	this.customerId = customerId;
	this.name = name;
	this.email = email;
	this.mobileNumber = mobileNumber;
}
public customer() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "customer [customerId=" + customerId + ", name=" + name + ", email=" + email + ", mobileNumber="
			+ mobileNumber + "]";
}



}
