package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.CustomerDTO;
import com.eazybank.accounts.entity.customer;

public class CustomerMapper {
	
	 public static CustomerDTO mapToCustomerDto(customer customer, CustomerDTO customerDto) {
	        customerDto.setName(customer.getName());
	        customerDto.setEmail(customer.getEmail());
	        customerDto.setMobileNumber(customer.getMobileNumber());
	        return customerDto;
	    }

	    public static customer mapToCustomer(CustomerDTO customerDto, customer customer) {
	        customer.setName(customerDto.getName());
	        customer.setEmail(customerDto.getEmail());
	        customer.setMobileNumber(customerDto.getMobileNumber());
	        return customer;
	    }

}
