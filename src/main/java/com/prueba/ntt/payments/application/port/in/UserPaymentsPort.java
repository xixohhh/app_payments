package com.prueba.ntt.payments.application.port.in;

import java.util.List;

import com.prueba.ntt.payments.domain.UserPayment;

public interface UserPaymentsPort {
	List<UserPayment> list(Long id);
}
