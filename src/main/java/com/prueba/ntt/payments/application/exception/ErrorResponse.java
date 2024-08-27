package com.prueba.ntt.payments.application.exception;


import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
	private String message;
    private Integer httpCode;
    private ZonedDateTime timestamp;
}
