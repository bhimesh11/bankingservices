package com.eazybank.accounts.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybank.accounts.dto.CardsDTO;

import jakarta.validation.constraints.Pattern;

@FeignClient("cards")
public interface CardsFeignClient {
	
	//abstact method sholud math api method inside cards
	@GetMapping(value = "/api/cards/fetch",consumes = "application/json")
	public ResponseEntity<CardsDTO> fetchCardDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber);


}
