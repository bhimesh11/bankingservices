package com.eazybank.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybank.accounts.dto.CustomerDetails;
import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.ErrorResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.core.MediaType;


@Tag( name = "REST API for Customers in EazyBank",
description = "REST APIs in EazyBank to FETCH customer details")
@RestController
@RequestMapping(value = "api",produces = {MediaType.APPLICATION_JSON})
@Validated
public class CustomerController {

	private final com.eazybank.accounts.service.icustomerService icustomerService;

	public CustomerController(com.eazybank.accounts.service.icustomerService icustomerService) {
		super();
		this.icustomerService = icustomerService;
	}
	
	   @Operation(
	            summary = "Fetch Customer Details REST API",
	            description = "REST API to fetch Customer details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "500",
	                    description = "HTTP Status Internal Server Error",
	                    content = @Content(
	                            schema = @Schema(implementation = ErrorResponseDTO.class)
	                    )
	            )
	    }
	    )

		@GetMapping("/fetchCustomerDetails")
		public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                String mobileNumber)
		{
		CustomerDetailsDto cs = icustomerService.fetcCustomerDetailsDto(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cs);
		}
	
	
}
