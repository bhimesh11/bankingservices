package com.eazybank.accounts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybank.accounts.dto.LoansDTO;

@Component
public class loansFallBack implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDTO> fetchLoanDetails(String correlationId, String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
