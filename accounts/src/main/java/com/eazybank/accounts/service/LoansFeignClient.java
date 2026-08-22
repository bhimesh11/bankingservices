package com.eazybank.accounts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybank.accounts.dto.LoansDTO;

import jakarta.validation.constraints.Pattern;

@FeignClient(name = "loans",fallback = loansFallBack.class)
public interface LoansFeignClient {
	
	//abstact method sholud math api method inside cards
	 @GetMapping("/api/loans/fetch")
	    ResponseEntity<LoansDTO> fetchLoanDetails(
	      @RequestParam String mobileNumber,  @RequestHeader("eazybank-correlation-id") String correlationId);
	 

}
