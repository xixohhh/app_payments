package com.prueba.ntt.payments.application.port.in;

import java.time.LocalDateTime;

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
	
	Double ammount;
	String creditCard;
	String description;
	LocalDateTime paymentDate;
	Long idUser;
	
}
