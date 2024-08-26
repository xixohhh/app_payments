package com.prueba.ntt.payments.application.port.out;

import com.prueba.ntt.payments.domain.UserPayment;

public interface  LoadPaymentPort {
	void load(UserPayment userPayment);
}
