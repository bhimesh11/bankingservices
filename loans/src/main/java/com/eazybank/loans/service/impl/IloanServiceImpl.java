package com.eazybank.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybank.loans.constants.LoansConstants;
import com.eazybank.loans.dto.LoansDTO;
import com.eazybank.loans.entity.Loans;
import com.eazybank.loans.exception.LoanAlreadyExistsException;
import com.eazybank.loans.exception.ResourceNotFoundException;
import com.eazybank.loans.mapper.LoansMapper;
import com.eazybank.loans.repository.LoansRepository;
import com.eazybank.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IloanServiceImpl implements ILoanService {

	private LoansRepository loansRepository;
	
	@Override
	public void createLoan(String mobileNUmber) {
		Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNUmber);
		if(optionalLoans.isPresent())
		{
			throw new LoanAlreadyExistsException("Please clear existing Loan to get a new Loan");
		}
		loansRepository.save(createNewLoan(mobileNUmber));
	}

	private Loans createNewLoan(String mobileNUmber) {
		Loans newLoan =new Loans();
		 long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		 newLoan.setLoanNumber(Long.toString(randomLoanNumber));
		 newLoan.setMobileNumber(mobileNUmber);
		 newLoan.setLoanType(LoansConstants.HOME_LOAN);
		 newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
		 newLoan.setAmountPaid(0);
		 newLoan.setOutStandingAmount(LoansConstants.NEW_LOAN_LIMIT);
		 return newLoan;
	}

	@Override
	public LoansDTO fetchLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
		return LoansMapper.maptoLoansDto(loans, new LoansDTO());
	}

	@Override
	public boolean updateLoan(LoansDTO loansDTO) {
		System.out.println("Hi from service ");
		Loans loans = loansRepository.findByLoanNumber(loansDTO.getLoanNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Loan number", loansDTO.getLoanNumber()));
		LoansMapper.maptoLoans(loans, loansDTO);
		loansRepository.save(loans);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber",mobileNumber));
		loansRepository.deleteById(loans.getLoanId());
		return true;
	}

}
