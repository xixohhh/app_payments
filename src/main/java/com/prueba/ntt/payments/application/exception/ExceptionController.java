package com.prueba.ntt.payments.application.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(GenericAppException.class)
	ResponseEntity<ErrorResponse> genericAppExceptionHandler(
			GenericAppException ex, HttpServletRequest request) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setHttpCode(ex.getHttpCode());
        errorResponse.setTimestamp(ex.getTimestamp());
        
		return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(ex.getHttpCode()));
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	ResponseEntity<ErrorResponse> conversionExceptionHandler(
			HttpMessageNotReadableException ex, HttpServletRequest request) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setHttpCode(400);
        errorResponse.setTimestamp(ZonedDateTime.now());
        
		return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(400));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> validationExceptionHandler(
			MethodArgumentNotValidException ex, HttpServletRequest request) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		
        errorResponse.setMessage(ex.getBindingResult().getAllErrors().toString());
        errorResponse.setHttpCode(400);
        errorResponse.setTimestamp(ZonedDateTime.now());
        
		return new ResponseEntity<>(errorResponse,HttpStatus.valueOf(400));
	}
}

