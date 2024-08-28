package com.prueba.ntt.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.ntt.payments.adapter.out.persistence.PaymentEntity;
import com.prueba.ntt.payments.adapter.out.persistence.PaymentMapper;
import com.prueba.ntt.payments.adapter.out.persistence.PaymentPersistenceAdapter;
import com.prueba.ntt.payments.adapter.out.persistence.UserEntity;
import com.prueba.ntt.payments.adapter.out.persistence.UserMapper;
import com.prueba.ntt.payments.adapter.out.persistence.UsersPaymentRepository;
import com.prueba.ntt.payments.adapter.out.persistence.UsersRepository;
import com.prueba.ntt.payments.domain.UserPayment;

@ExtendWith(MockitoExtension.class)
class PaymentPersistenceAdapterTest {
	
	@Mock
	private UsersPaymentRepository usersPaymentRepository;
	
	@Mock
	private UsersRepository usersRepository;

	
	@InjectMocks
	private PaymentPersistenceAdapter paymentPersistenceAdapter;
	

	
	@Test
	void test_loadUser_exito(){
		
		UserPayment payment1 = new UserPayment(2L,0L,18.20,"12332112313131",LocalDateTime.now(),"sdadas");
		Optional<UserEntity> expectedUser= Optional.of(new UserEntity(0L, "test", "test", "test", "test"));

		
		when(usersRepository.findById(payment1.getIdUser())).thenReturn(expectedUser);
		
		UserEntity user = UserMapper.domainToEntity(paymentPersistenceAdapter.load(payment1.getIdUser()));
		
		
		assertEquals(user,expectedUser.get());
	}
	
	@Test
	void test_listUserPayments_exito(){
		
		
		Optional<UserEntity> user = Optional.of(new UserEntity(0L, "test", "test", "test", "test"));
		
		LocalDateTime fecha = LocalDateTime.now();
		
		UserPayment payment1 = new UserPayment(1L,0L,18.20,"12332112313131",fecha,"sdadas");
		UserPayment payment2 = new UserPayment(2L,0L,17.20,"12332112313131",fecha,"sdadas");

		
		List<UserPayment> expectedPayments = Arrays.asList(payment1, payment2);
		
		List<PaymentEntity> expectedEntityPayments = Arrays.asList(PaymentMapper.domainToEntity(payment1), PaymentMapper.domainToEntity(payment2));
		
		when(usersPaymentRepository.findByIdUser(user.get().getId())).thenReturn(expectedEntityPayments);
		
		List<UserPayment> userPayments = paymentPersistenceAdapter.list(user.get().getId());
		
		assertEquals(expectedPayments,userPayments);
	}
	

}
