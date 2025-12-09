package com.eazybank.accounts.dto;
import java.util.*;

import org.springframework.boot.context.properties.ConfigurationProperties;
//data carrier classes
//able to read to not able to change 
//what ever the field we are going to pass while object creation are final and are able to read with getter method
@ConfigurationProperties(prefix = "accounts")
public class AccountsContactInfoDto {
	
	private String Message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public Map<String, String> getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(Map<String, String> contactDetails) {
		this.contactDetails = contactDetails;
	}
	public List<String> getOnCallSupport() {
		return onCallSupport;
	}
	public void setOnCallSupport(List<String> onCallSupport) {
		this.onCallSupport = onCallSupport;
	}
	
	
	

}
