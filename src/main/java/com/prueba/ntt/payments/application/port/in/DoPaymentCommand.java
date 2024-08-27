package com.prueba.ntt.payments.application.port.in;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoPaymentCommand {
	
	BigDecimal ammount;
	String creditCard;
	String description;
	Date paymentDate;
	Long idUser;
	
}
