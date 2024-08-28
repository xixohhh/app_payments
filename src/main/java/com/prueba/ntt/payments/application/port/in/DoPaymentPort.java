package com.prueba.ntt.payments.application.port.in;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("doPaymentPort")
public interface DoPaymentPort {
	 public boolean send(DoPaymentCommand command);
}
