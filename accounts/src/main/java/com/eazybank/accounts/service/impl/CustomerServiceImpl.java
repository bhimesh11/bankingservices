package com.eazybank.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eazybank.accounts.dto.AccountsDTO;
import com.eazybank.accounts.dto.CardsDTO;
import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.LoansDTO;
import com.eazybank.accounts.entity.accounts;
import com.eazybank.accounts.entity.customer;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountsMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerReposiotry;
import com.eazybank.accounts.service.CardsFeignClient;
import com.eazybank.accounts.service.LoansFeignClient;
import com.eazybank.accounts.service.icustomerService;

@Service
public class CustomerServiceImpl implements icustomerService {

	private AccountsRepository accountsRepository;
	private CustomerReposiotry customerReposiotry;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	
	@Override
	public CustomerDetailsDto fetcCustomerDetailsDto(String mobileNUmber) {
		customer customer = customerReposiotry.findBymobileNumber(mobileNUmber).orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNUmber));
		accounts accounts = accountsRepository.findBycustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId", customer.getCustomerId().toString()));
		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapttoCustomerDetailsDto(customer, new CustomerDetailsDto());
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountDTO(accounts, new AccountsDTO()));
		
		ResponseEntity<LoansDTO> loaResponseEntity  = loansFeignClient.fetchLoanDetails(mobileNUmber);
		customerDetailsDto.setLoansDto(loaResponseEntity.getBody());
		
		ResponseEntity<CardsDTO> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNUmber);
		customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
		
		return customerDetailsDto;
	}

}
