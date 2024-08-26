package com.prueba.ntt.payments.adapter.in.web.dto;

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
public class PaymentDTO {
	BigDecimal ammount;
	String creaditCard;
	String description;
	Date paymentDate;
}
