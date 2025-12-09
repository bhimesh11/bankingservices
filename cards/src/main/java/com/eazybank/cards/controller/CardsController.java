package com.eazybank.cards.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.eazybank.cards.constants.CardsConstants;
import com.eazybank.cards.dto.CardContactInfo;
import com.eazybank.cards.dto.CardsDTO;
import com.eazybank.cards.dto.ErrorResponseDTO;
import com.eazybank.cards.dto.ResponseDto;
import com.eazybank.cards.dto.buildVersion;
import com.eazybank.cards.service.IcardsService;
import com.eazybank.cards.service.impl.ICardServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@Tag(  name = "CRUD REST APIs for Cards in EazyBank",
description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details" )

@RestController
@Validated
@RequestMapping(path = "/api/cards", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CardsController {

	private IcardsService icardsService;
	@Autowired
	private Environment environment;
	@Autowired
	private buildVersion buildVersion;
	
	@Autowired
	private CardContactInfo cardContactInfo;

	public CardsController(IcardsService icardsService) {
		super();
		this.icardsService = icardsService;
	}

	@Operation(summary = "Create cards Rest API",description ="REST API to create new Card inside EazyBank" )
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Http Status CREATED"),
		@ApiResponse(responseCode = "500",description = "Http staus Internal server error",
		content = @Content(schema =  @Schema(implementation = ErrorResponseDTO.class)))
	})
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createCard(
			@Validated @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		icardsService.createCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
	}
	   @Operation(
	            summary = "Fetch Card Details REST API",
	            description = "REST API to fetch card details based on a mobile number"
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
	    })
	@GetMapping("/fetch")
	public ResponseEntity<CardsDTO> fetchCardDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber)

	{
		CardsDTO cardsDTO = icardsService.fetchCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(cardsDTO);

	}
	   @Operation(
	            summary = "Update Card Details REST API",
	            description = "REST API to update card details based on a card number"
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
	        })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateDetails(@Valid @RequestBody CardsDTO cardsDTO) {
		boolean isUpdated = icardsService.updateCard(cardsDTO);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.STATUS_201));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.STATUS_417));
		}
	}
	   @Operation(
	            summary = "Delete Card Details REST API",
	            description = "REST API to delete Card details based on a mobile number"
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
	    })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteCard(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		boolean isDeleted = icardsService.deleteCard(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.STATUS_201));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.STATUS_417));
		}

	}
	   @Operation(summary = "Get Build Information",
				description = "Get Build Information that is deployed into cards microservice")
		@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
				@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",content = @Content(schema =  @Schema(implementation = ErrorResponseDTO.class))) })
	   @GetMapping("/build-info")
	   public ResponseEntity<buildVersion> getBuildInfo()
	   {
		return  ResponseEntity.status(HttpStatus.OK).body(buildVersion);
		   
	   }
	   @Operation(summary = "Get java version Information",
				description = "Get java Information that is deployed into cards microservice")
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
	   public ResponseEntity<CardContactInfo> getContactInfo()
	   {
		return  ResponseEntity.status(HttpStatus.OK).body(cardContactInfo);
		   
	   }
}
