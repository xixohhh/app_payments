package com.prueba.ntt.payments.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPayment {

	private Long id;

	private Long idUser;

	private Double ammount;

	private String cardNumber;

	private LocalDateTime paymentDate;

}
