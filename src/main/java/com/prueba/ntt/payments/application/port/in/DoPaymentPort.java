package com.prueba.ntt.payments.application.port.in;

public interface DoPaymentPort {
	 public boolean send(DoPaymentCommand command);
}
