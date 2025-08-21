package com.eazybank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
@Schema(name = "Loans",description = "Schema to hold Loans information")
@Data
public class LoansDTO {
	 @NotEmpty(message = "Mobile Number can not be a null or empty")
	 @Pattern(regexp="(^$|[0-9]{10})",message = "LoanNumber must be 10 digits")
	 @Schema(description = "MobileNumber of the customer",example = "4365327698" )
	private String mobileNumber;
	@Schema(description = "LoanApplicationNumber of the customer",example = "4365327698" )
	@NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
	private String loanNumber;
	@NotEmpty(message =  "LoanType can not be a null or empty" )
	@Schema(description = "Type of the loan", example = "Home loan/car Loan")
	private String loanType;
	@Positive(message = "Total loan amount should be greater than zero")
	@Schema(description = "Total Loan amount taken by customer", example = "Home loan")
	private int totalLoan;
	 @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
	   
	  @Schema(
	            description = "Total loan amount paid", example = "1000"
	    )	
	private int amountPaid;
	 @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
	    @Schema(
	            description = "Total outstanding amount against a loan", example = "99000"
	    )
	private int outStandingAmount;
}
