package com.eazybank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class accounts extends BaseEntity {
	

	@Column(name="customer_id")
	private Long customerId;
	@Id
	private Long account_number;
    @Column(name="account_type")
	private String account_type;
    @Column(name="branch_address")
	private String branch_address;
	public Long getCustomer_id() {
		return customerId;
	}
	public void setCustomer_id(Long customer_id) {
		this.customerId = customer_id;
	}
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
	public accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public accounts(Long customer_id, Long account_number, String account_type, String branch_address) {
		super();
		this.customerId = customer_id;
		this.account_number = account_number;
		this.account_type = account_type;
		this.branch_address = branch_address;
	}
	@Override
	public String toString() {
		return "accounts [customer_id=" + customerId + ", account_number=" + account_number + ", account_type="
				+ account_type + ", branch_address=" + branch_address + "]";
	}

}
