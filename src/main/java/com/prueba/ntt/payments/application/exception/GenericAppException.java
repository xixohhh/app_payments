package com.prueba.ntt.payments.application.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class GenericAppException extends RuntimeException{


	private static final long serialVersionUID = -6342276173515048860L;
	
	private final String message;
	private final Integer httpCode;
	private final ZonedDateTime timestamp;
	
	public GenericAppException(String message,Integer httpCode) {
		super(message);
		this.message = message;
		this.httpCode = httpCode;
		this.timestamp = ZonedDateTime.now();
		
	}

}
