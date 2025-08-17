package com.eazybank.accounts.service;

import org.springframework.stereotype.Service;

import com.eazybank.accounts.dto.CustomerDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


public interface AccountsService {
/**
 * 
 * @param customerDTO - CustomerDto object
 */
	void createAccount (CustomerDTO customerDTO);

CustomerDTO fetchDetails(
		@Pattern(regexp = "^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber);

boolean updateAccount(@Valid CustomerDTO customerDTO);
public boolean deleteAccount(String mobileNumber);
}
