package com.prueba.ntt.payments.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersPaymentRepository  extends JpaRepository<PaymentEntity, Long>{
	
	List<PaymentEntity> findByIdUser(Long id);
}
