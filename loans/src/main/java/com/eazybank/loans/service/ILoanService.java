package com.eazybank.loans.service;

import com.eazybank.loans.dto.LoansDTO;

public interface ILoanService {
	
	void createLoan(String mobileNUmber);
	LoansDTO fetchLoan(String mobileNumber);
	boolean updateLoan(LoansDTO loansDTO);
	boolean deleteLoan(String mobileNumber);

}
