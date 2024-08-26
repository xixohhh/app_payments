package com.prueba.ntt.payments.adapter.out.persistence;


import java.util.List;
import java.util.stream.Collectors;

import com.prueba.ntt.payments.application.port.out.LoadPaymentPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPaymentsPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPort;
import com.prueba.ntt.payments.common.PersistenceAdapter;
import com.prueba.ntt.payments.domain.User;
import com.prueba.ntt.payments.domain.UserPayment;

@PersistenceAdapter
public class PaymentPersistenceAdapter implements LoadPaymentPort,LoadUserPort,LoadUserPaymentsPort {
	
	private final UsersPaymentRepository usersPaymentRepository;
	private final UsersRepository usersRepository;
	
	public PaymentPersistenceAdapter(UsersPaymentRepository usersPaymentRepository, UsersRepository usersRepository) {
		this.usersPaymentRepository = usersPaymentRepository;
		this.usersRepository = usersRepository;
	}



	@Override
	public void load(UserPayment userPayment) {
		usersPaymentRepository.save(PaymentMapper.domainToEntity(userPayment));
		
	}


	@Override
	public User load(Long id) {
		
		return usersRepository.findById(id).map(UserMapper::entityToDomain).orElseThrow(RuntimeException::new);
	}



	@Override
	public List<UserPayment> list(Long id) {
		return usersPaymentRepository.findByIdUser(id).stream().map( pay -> PaymentMapper.entityToDomain(pay)).collect(Collectors.toList());
	}



}
