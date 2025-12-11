package com.eazybank.accounts.service;

import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.entity.customer;

public interface icustomerService {

	CustomerDetailsDto fetcCustomerDetailsDto(String mobileNUmber);
}
