package com.eazybank.loans.mapper;

import com.eazybank.loans.dto.LoansDTO;
import com.eazybank.loans.entity.Loans;

public class LoansMapper {

	
	public static LoansDTO maptoLoansDto(Loans loans,LoansDTO loansDTO)
	{
		loansDTO.setLoanNumber(loans.getLoanNumber());
		loansDTO.setLoanType(loans.getLoanType());
		loansDTO.setMobileNumber(loans.getMobileNumber());
		loansDTO.setTotalLoan(loans.getTotalLoan());
		loansDTO.setAmountPaid(loans.getAmountPaid());
		loansDTO.setOutstandingAmount(loans.getOutStandingAmount());
		
		return loansDTO;
	}
	
	public static Loans maptoLoans(Loans loans,LoansDTO loansDTO)
	{
		loans.setLoanNumber(loansDTO.getLoanNumber());
		loans.setLoanType(loansDTO.getLoanType());
		loans.setMobileNumber(loansDTO.getMobileNumber());
		loans.setTotalLoan(loansDTO.getTotalLoan());
		loans.setAmountPaid(loansDTO.getAmountPaid());
		loans.setOutStandingAmount(loansDTO.getOutstandingAmount());
		
		return loans;
	}
}
