package com.prueba.ntt.payments.application.port.out;

import java.util.List;

import com.prueba.ntt.payments.domain.UserPayment;

public interface LoadUserPaymentsPort {
	List<UserPayment> list(Long id);
}
