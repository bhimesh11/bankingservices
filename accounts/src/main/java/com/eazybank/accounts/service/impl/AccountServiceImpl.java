package com.eazybank.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eazybank.accounts.Genericconstants.AccountConstants;
import com.eazybank.accounts.dto.AccountsDTO;
import com.eazybank.accounts.dto.CustomerDTO;
import com.eazybank.accounts.entity.accounts;
import com.eazybank.accounts.entity.customer;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountsMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerReposiotry;
import com.eazybank.accounts.service.AccountsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

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
		System.out.println("Verify the user...");
		Optional<customer> customerValidation = customerReposiotry.findBymobileNumber(customerDTO.getMobileNumber());
		if (customerValidation.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Customer with this mobile number already exists");
		} else {
			customer.setCreatedAt(LocalDateTime.now());
			customer.setCreatedBy("admin");
			customer savedCustomer = customerReposiotry.save(customer);
			accountsRepository.save(createNewAccount(savedCustomer));
		}

	}

	private accounts createNewAccount(customer savedCustomer) {
		accounts newAccount = new accounts();
		newAccount.setCustomer_id(savedCustomer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

		newAccount.setAccount_number(randomAccNumber);
		newAccount.setAccount_type(AccountConstants.SAVINGS);
		newAccount.setBranch_address(AccountConstants.ADDRESS);
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCreatedBy("admin");

		return newAccount;
	}

	@Override
	public CustomerDTO fetchDetails(
			@Pattern(regexp = "^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {

		customer customer = customerReposiotry.findBymobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("customer", "mobilenumber", mobileNumber));
		accounts accounts = accountsRepository.findBycustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

		CustomerDTO customerDTO = CustomerMapper.mapToCustomerDto(customer, new CustomerDTO());
		customerDTO.setAccountsDTO(AccountsMapper.mapToAccountDTO(accounts, new AccountsDTO()));

		return customerDTO;
	}

	@Override
	public boolean updateAccount(@Valid CustomerDTO customerDTO) {
		boolean isUpdated = false;
		AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
		if (accountsDTO != null) {
			accounts accounts = accountsRepository.findById(accountsDTO.getAccount_number())
					.orElseThrow(() -> new ResourceNotFoundException("Accounts", "AccountNumber",
							accountsDTO.getAccount_number().toString()));
			AccountsMapper.mapToAccounts(accountsDTO, new accounts());
			accounts = accountsRepository.save(accounts);

			Long customerId = accounts.getCustomer_id();
			customer customer = customerReposiotry.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString()));
			CustomerMapper.mapToCustomer(customerDTO, customer);
			customerReposiotry.save(customer);
			isUpdated = true;

		}
		return isUpdated;
	}

	public boolean deleteAccount(String mobileNumber) {
		customer customer = customerReposiotry.findBymobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("customer", "mobilenumber", mobileNumber));

		accountsRepository.deleteByCustomerId(customer.getCustomerId());
		customerReposiotry.deleteById(customer.getCustomerId());
		return true;
	}

}
