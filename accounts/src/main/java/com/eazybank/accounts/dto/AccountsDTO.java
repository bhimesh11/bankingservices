package com.eazybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(name = "Accounts", description = "Schema to hold account information ")

public class AccountsDTO {

	@Schema(description = "Eazy Bank Account number ")
	@NotEmpty(message = "AccountNumber Cannot be null or empty")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Mobile number should be 10 digits and start with 6-9")
	private Long account_number;

	@Schema(description = "Account type of Eazy Bank account",
			example = "Savings")
	@NotEmpty(message = "AccountType cannot be null or empty")
	private String account_type;

	@Schema(description = "Eazy Bank branch address", 
			example = "123 NewYork")
	@NotEmpty(message = "Branch address cannot be null or empty")
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

	public AccountsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountsDTO(Long account_number, String account_type, String branch_address) {
		super();
		this.account_number = account_number;
		this.account_type = account_type;
		this.branch_address = branch_address;
	}

	@Override
	public String toString() {
		return "AccountsDTO [account_number=" + account_number + ", account_type=" + account_type + ", branch_address="
				+ branch_address + "]";
	}

}
