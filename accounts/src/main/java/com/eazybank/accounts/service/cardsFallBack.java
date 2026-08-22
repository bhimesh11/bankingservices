package com.eazybank.accounts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybank.accounts.dto.CardsDTO;

import jakarta.validation.constraints.Pattern;

@Component
public class cardsFallBack implements CardsFeignClient {

	@Override
	public ResponseEntity<CardsDTO> fetchCardDetails(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber,
			String correlationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
