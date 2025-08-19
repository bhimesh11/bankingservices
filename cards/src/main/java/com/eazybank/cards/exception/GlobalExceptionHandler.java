package com.eazybank.cards.exception;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eazybank.cards.dto.ErrorResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest webRequest)
	{
		Map<String , String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
		validationErrorList.forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});
		return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex,WebRequest webRequest)
	{
		ErrorResponseDTO errorresponseDto = new ErrorResponseDTO(
				webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorresponseDto);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest webRequest)
	{
		ErrorResponseDTO errorresponseDto = new ErrorResponseDTO(
				webRequest.getDescription(false),
				HttpStatus.INTERNAL_SERVER_ERROR,
				ex.getMessage(),LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorresponseDto);
	}
	
	 @ExceptionHandler(CardAlreadyExistsException.class)
	    public ResponseEntity<ErrorResponseDTO> handleCardAlreadyExistsException(CardAlreadyExistsException exception,
	                                                                          WebRequest webRequest){
	        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
	                webRequest.getDescription(false),
	                HttpStatus.BAD_REQUEST,
	                exception.getMessage(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	    }
}
