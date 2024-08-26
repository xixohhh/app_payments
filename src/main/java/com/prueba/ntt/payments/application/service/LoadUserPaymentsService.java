package com.prueba.ntt.payments.application.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prueba.ntt.payments.application.port.in.UserPaymentsPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPaymentsPort;
import com.prueba.ntt.payments.common.UseCase;
import com.prueba.ntt.payments.domain.UserPayment;

@UseCase
@Component
public class LoadUserPaymentsService implements UserPaymentsPort{
	
	private final LoadUserPaymentsPort loadUserPaymentsPort;
	
	public LoadUserPaymentsService(LoadUserPaymentsPort loadUserPaymentsPort) {
		this.loadUserPaymentsPort = loadUserPaymentsPort;
	}

	@Override
	public List<UserPayment> list(Long id) {
		
		return loadUserPaymentsPort.list(id);
	}
	
	
	
}
