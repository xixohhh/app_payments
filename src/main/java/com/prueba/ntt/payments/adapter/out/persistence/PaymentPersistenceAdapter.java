package com.prueba.ntt.payments.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import com.prueba.ntt.payments.application.port.out.LoadPaymentPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPaymentsPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPort;
import com.prueba.ntt.payments.common.PersistenceAdapter;
import com.prueba.ntt.payments.domain.User;
import com.prueba.ntt.payments.domain.UserPayment;

@PersistenceAdapter
public class PaymentPersistenceAdapter implements LoadPaymentPort, LoadUserPort, LoadUserPaymentsPort {

	private final UsersPaymentRepository usersPaymentRepository;
	private final UsersRepository usersRepository;

	public PaymentPersistenceAdapter(UsersPaymentRepository usersPaymentRepository, UsersRepository usersRepository) {
		this.usersPaymentRepository = usersPaymentRepository;
		this.usersRepository = usersRepository;
	}

	@Override
	public boolean load(UserPayment userPayment) {
		boolean ok = true;
		try {
			usersPaymentRepository.save(PaymentMapper.domainToEntity(userPayment));

		} catch (Exception e) {
			ok = false;
		}
		return ok;
	}

	@Override
	public User load(Long id) {

		return usersRepository.findById(id).map(UserMapper::entityToDomain).orElse(null);
	}

	@Override
	public List<UserPayment> list(Long id) {
		List<UserPayment> lista = new ArrayList<>();
		usersPaymentRepository.findByIdUser(id).forEach(e -> lista.add(PaymentMapper.entityToDomain(e)));
		return lista;
	}

}
