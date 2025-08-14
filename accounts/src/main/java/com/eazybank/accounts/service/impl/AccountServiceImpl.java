package com.eazybank.accounts.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eazybank.accounts.dto.CustomerDTO;
import com.eazybank.accounts.entity.customer;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerReposiotry;
import com.eazybank.accounts.service.AccountsService;

@Service
public class AccountServiceImpl implements AccountsService {

	private AccountsRepository accountsRepository;
	private CustomerReposiotry customerReposiotry;
	
	
	
	
	public AccountServiceImpl(AccountsRepository accountsRepository, CustomerReposiotry customerReposiotry) {
		super();
		this.accountsRepository = accountsRepository;
		this.customerReposiotry = customerReposiotry;
	}




	@Override
	public void createAccount(CustomerDTO customerDTO) {
		customer customer = CustomerMapper.mapToCustomer(customerDTO, new customer());
		Optional<customer> customerValidation = customerReposiotry.findByMobileNumber(customerDTO.getMobileNumber());
		if(customerValidation.isPresent())
		{
			//throw new customer
		}
	}

}
