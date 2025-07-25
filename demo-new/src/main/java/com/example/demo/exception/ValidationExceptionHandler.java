package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

// Create a new custom exception handler method below for NumberFormatException - Refer to ItemValidation.java
// Ensure error is log as below
// [demo] [nio-8080-exec-1] c.e.d.e.ValidationExceptionHandler       : NumberFormatException handled:
	public static final String STATUS = "status";
	public static final  String ERRORS = "errors";
	public static final String TYPE = "type";
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleRuntimeValidationException(RuntimeException ex) {

		Map<String, Object> responseBody = new HashMap<>();
		
		
		responseBody.put(TYPE, "runtime validation exception");
		responseBody.put(STATUS, HttpStatus.BAD_REQUEST.value());
		responseBody.put(ERRORS, ex.getMessage());

		ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(responseBody);

		if (log.isErrorEnabled()) {
			log.error("handleRuntimeValidationException Exception handled: {}", responseEntity);
		}
		return responseEntity;

	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Map<String, Object>> handleNumberFormatException(NumberFormatException ex) {

		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put(TYPE, "number format exception");
		responseBody.put(STATUS, HttpStatus.BAD_REQUEST.value());
		responseBody.put(ERRORS, ex.getMessage());


		ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(responseBody);

		if (log.isErrorEnabled()) {
			log.error("handleNumberFormatExceptionException handled: {}", responseEntity);
		}
		return responseEntity;

	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleInvalidItemNameException(IllegalArgumentException ex) {
	    Map<String, Object> responseBody = new HashMap<>();
	    responseBody.put(TYPE, "validation exception");
		responseBody.put(STATUS, HttpStatus.BAD_REQUEST.value());
		responseBody.put(ERRORS, ex.getMessage());


	    ResponseEntity<Map<String, Object>> responseEntity = ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(responseBody);

	    if (log.isErrorEnabled()) {
	        log.error("handleInvalidItemNameException handled: {}", responseEntity);
	    }

	    return responseEntity;
	}
	
}
