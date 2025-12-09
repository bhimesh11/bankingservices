package com.eazybank.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans")
public class LoanContactInfo {

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
