package com.prueba.ntt.payments.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BodyRequestMissingException extends GenericAppException{
	
	private static final long serialVersionUID = 3046708836104748972L;

	public BodyRequestMissingException(String message) {
		super(message, 409);
	}

}
