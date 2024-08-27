package com.prueba.ntt.payments.application.port.out;


import com.prueba.ntt.payments.domain.User;
public interface  LoadUserPort {
	User load(Long id);
}
