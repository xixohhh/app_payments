package com.prueba.ntt.payments.adapter.out.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users_payments")
public class PaymentEntity {
	
	@Id
	@SequenceGenerator(name = "users_payments_seq", sequenceName = "\"payments-db\".users_payments_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_payments_seq")
	@Column(name="id_payment")
	private Long id;
	
	@Column(name="id_user")
	private Long idUser;
	
	@Column(name="ammount")
	private Double ammount;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="payment_date")
	private LocalDateTime paymentDate;
	
	@Column(name="description")
	private String description;
	
}
