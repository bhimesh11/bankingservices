package com.eazybank.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Schema(name = "ErrorResponse", description = "Schema to hold error response information")
public class ErrorResponseDTO {

	@Schema(description = "API path invoked by client")
private String apiPath;
	@Schema(description = "Error code representing the error happend")
  private HttpStatus errorCode;
	@Schema(description = "Error message representing the error happend")
  private String errorMessage;
	@Schema(description = "Time representing when the error happend")
  private LocalDateTime errorTime;
	
	
	
}
