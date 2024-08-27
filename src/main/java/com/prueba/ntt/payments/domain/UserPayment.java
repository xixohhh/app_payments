package com.prueba.ntt.payments.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPayment {

	private Long id;

	private Long idUser;

	private BigDecimal ammount;

	private String cardNumber;

	private Date paymentDate;

}
