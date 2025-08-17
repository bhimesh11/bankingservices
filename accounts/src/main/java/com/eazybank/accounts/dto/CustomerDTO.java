package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Schema(name = "Customer",
description = "Schema to hold customer and account information" )
public class CustomerDTO {
@Schema( description = "Name of the customer ", example = "Bhimesh Ganji")
@NotEmpty(message = "Name cannot be empty")
@Size(min=5,max = 30,message = "The length of the customer name should be between 5 and 30")
private String name;
@Schema( description = "Customer Email Address ", example = "bhimeshganji5@gmail.com")
@NotEmpty(message = "Email id cannot be empty")
@Email(message = "Email address should be valid")
private String email;

@NotEmpty
@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number should be 10 digits and start with 6-9")
private String mobileNumber;

private AccountsDTO accountsDTO;




public AccountsDTO getAccountsDTO() {
	return accountsDTO;
}
public void setAccountsDTO(AccountsDTO accountsDTO) {
	this.accountsDTO = accountsDTO;
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

public CustomerDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public CustomerDTO(String name, String email, String mobileNumber, AccountsDTO accountsDTO) {
	super();
	this.name = name;
	this.email = email;
	this.mobileNumber = mobileNumber;
	this.accountsDTO = accountsDTO;
}

@Override
public String toString() {
	return "CustomerDTO [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountsDTO="
			+ accountsDTO + "]";
}



}
