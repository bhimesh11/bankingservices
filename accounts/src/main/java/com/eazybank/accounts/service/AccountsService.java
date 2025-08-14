package com.eazybank.accounts.service;

import org.springframework.stereotype.Service;

import com.eazybank.accounts.dto.CustomerDTO;


public interface AccountsService {
/**
 * 
 * @param customerDTO - CustomerDto object
 */
	void createAccount (CustomerDTO customerDTO);
	
}
