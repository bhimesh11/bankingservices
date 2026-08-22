package com.eazybank.loans.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.eazybank.loans.constants.LoansConstants;
import com.eazybank.loans.dto.ErrorResponseDTO;
import com.eazybank.loans.dto.LoanContactInfo;
import com.eazybank.loans.dto.LoansDTO;
import com.eazybank.loans.dto.ResponseDTO;
import com.eazybank.loans.dto.buildVersion;
import com.eazybank.loans.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;



@Tag(name = "CRUD REST APIS FOR LOANS IN EAZYBANK",
description = "CRUD REST API IN EAZYBANK TO CREATE,UPDATE,FETCH and delete loan details")
@RestController
@RequestMapping(path = "/api/loans",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoansController {

	@Autowired
	private ILoanService iLoanService;
	@Autowired
	private Environment environment;
	
	@Autowired
	private buildVersion buildVersion;
	
	@Autowired
	private LoanContactInfo loanContactInfo;
	
	private static final Logger logger = LoggerFactory.getLogger(LoansController.class);
	
	@Operation(summary = "Create Loan Rest API", 
			description = "REST API to create new loans inside eazybank")
	@ApiResponses({
		@ApiResponse(responseCode = "201",description = "HTTP Status created"),
		@ApiResponse(responseCode = "500", description = "HTTP status internal server error", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
	})
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createLoan(@RequestParam
			 @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	String mobileNumber)
	{
		System.out.println(mobileNumber);
		iLoanService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(LoansConstants
				.STATUS_201, LoansConstants.MESSAGE_201));
	}
	 @Operation(
	            summary = "Fetch Loan Details REST API",
	            description = "REST API to fetch loan details based on a mobile number"
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
	    @GetMapping("/fetch")
	    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam
	    		@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	                                                               String mobileNumber,@RequestHeader("eazybank-correlation-id") String correaltionId) {
		 logger.debug("eazyBank-correlation-id found: {} ", correaltionId);
		 LoansDTO loansDTO = iLoanService.fetchLoan(mobileNumber);
		 return ResponseEntity.status(HttpStatus.OK).body(loansDTO);
		
	 }
	  @Operation(
	            summary = "Update Loan Details REST API",
	            description = "REST API to update loan details based on a loan number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
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
	  @PutMapping("/update")
	    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoansDTO loansDto) {
	        boolean isUpdated = iLoanService.updateLoan(loansDto);
	        if(isUpdated) {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
	        }else{
	            return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
	        }
	    }
		  
	  @Operation(
	            summary = "Delete Loan Details REST API",
	            description = "REST API to delete Loan details based on a mobile number"
	    )
	    @ApiResponses({
	            @ApiResponse(
	                    responseCode = "200",
	                    description = "HTTP Status OK"
	            ),
	            @ApiResponse(
	                    responseCode = "417",
	                    description = "Expectation Failed"
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
	  @DeleteMapping("/delete")
	    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam
	                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	                                                                String mobileNumber) {
		  boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
		  if(isDeleted)
		  {
			  return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200)); 
		  }else {
			  return ResponseEntity
	                    .status(HttpStatus.EXPECTATION_FAILED)
	                    .body(new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
		  }
	  }

	  @Operation(summary = "Get Build Information",
				description = "Get Build Information that is deployed into loans microservice")
		@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
				@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema =  @Schema(implementation = ErrorResponseDTO.class))) })
	  
	   @GetMapping("/build-info")
	   public ResponseEntity<buildVersion> getBuildInfo()
	   {
		return  ResponseEntity.status(HttpStatus.OK).body(buildVersion);
		   
	   }
	   @Operation(summary = "Get java version Information",
				description = "Get java Information that is deployed into loans microservice")
		@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
				@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema =  @Schema(implementation = ErrorResponseDTO.class))) })
	   @GetMapping("/java-version")
	   public ResponseEntity<String> getJavaVersion()
	   {
		return  ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
		   
	   }
		@Operation(summary = "Get Contact info",
				description = "contact info details that can be reached out in case of any issues")
		@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
				@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema =  @Schema(implementation = ErrorResponseDTO.class))) })
	   @GetMapping("/contact-info")
	   public ResponseEntity<LoanContactInfo> getContactInfo()
	   {
			logger.debug("Invoked Loans contact-info api");
		return  ResponseEntity
				.status(HttpStatus.OK)
				.body(loanContactInfo);
		   
	   }   
}
