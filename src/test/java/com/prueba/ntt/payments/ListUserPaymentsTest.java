package com.prueba.ntt.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.prueba.ntt.payments.adapter.out.persistence.UsersPaymentRepository;
import com.prueba.ntt.payments.application.port.out.LoadUserPaymentsPort;
import com.prueba.ntt.payments.application.service.LoadUserPaymentsService;
import com.prueba.ntt.payments.domain.UserPayment;

@ExtendWith(MockitoExtension.class)
class ListUserPaymentsTest {
	
	
	@Mock
	UsersPaymentRepository usersPaymentRepository;
	
	@Mock
	private LoadUserPaymentsPort repository;
	
	@InjectMocks
	private LoadUserPaymentsService paymentService;
	
	@Test
	void test_getAllUserPaymentsRepository_exito(){
		
		//Given
		UserPayment payment1 = new UserPayment(1L,0L,18.20,"12332112313131",LocalDateTime.now(),"sdadas");
		UserPayment payment2 = new UserPayment(1L,0L,17.20,"12332112313131",LocalDateTime.now(),"sdadas");
		
		List<UserPayment> expectedPayments = Arrays.asList(payment1, payment2);
		
		when(repository.list(0L)).thenReturn(expectedPayments);
		//WHEN
		List<UserPayment> userPayments = paymentService.list(0L);

		//THEN
		assertEquals(userPayments,expectedPayments);

	}
	@Test
	void test_getAllUserPaymentsService_exito(){
		
		//Given

		UserPayment userPayment1 = new UserPayment(1L,0L,18.20,"12332112313131",LocalDateTime.now(),"sdadas");
		UserPayment userPayment2 = new UserPayment(1L,0L,17.20,"12332112313131",LocalDateTime.now(),"sdadas");

		List<UserPayment> expectedUserPayments = Arrays.asList(userPayment1, userPayment2);
		
		when(paymentService.list(0L)).thenReturn(expectedUserPayments);
		//WHEN
		List<UserPayment> userPayments = paymentService.list(0L);

		//THEN
		assertEquals(userPayments,expectedUserPayments);

	}


}
