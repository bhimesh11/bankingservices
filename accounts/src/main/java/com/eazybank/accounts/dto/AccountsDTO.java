package com.eazybank.accounts.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class AccountsDTO {
	
	private Long account_number;

	private String account_type;
	
	private String branch_address;
	
	
	public Long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(Long account_number) {
		this.account_number = account_number;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getBranch_address() {
		return branch_address;
	}
	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}
	@Override
	public String toString() {
		return "AccountsDTO [account_number=" + account_number + ", account_type=" + account_type + ", branch_address="
				+ branch_address + "]";
	}
	public AccountsDTO(Long account_number, String account_type, String branch_address) {
		super();
		this.account_number = account_number;
		this.account_type = account_type;
		this.branch_address = branch_address;
	}
	public AccountsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
