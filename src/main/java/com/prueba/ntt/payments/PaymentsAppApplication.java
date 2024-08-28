package com.prueba.ntt.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PaymentsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsAppApplication.class, args);
	}

}
