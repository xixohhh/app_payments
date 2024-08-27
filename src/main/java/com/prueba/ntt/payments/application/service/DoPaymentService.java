package com.prueba.ntt.payments.application.service;

import org.springframework.stereotype.Component;

import com.prueba.ntt.payments.application.exception.UserNotFoundException;
import com.prueba.ntt.payments.application.port.in.DoPaymentCommand;
import com.prueba.ntt.payments.application.port.in.DoPaymentPort;
import com.prueba.ntt.payments.application.port.out.LoadPaymentPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPort;
import com.prueba.ntt.payments.common.UseCase;
import com.prueba.ntt.payments.domain.User;
import com.prueba.ntt.payments.domain.UserPayment;

import jakarta.transaction.Transactional;


@UseCase
@Component
public class DoPaymentService implements DoPaymentPort{

	private final LoadUserPort loadUserPort;
	private final LoadPaymentPort loadPaymentPort;
	
	public DoPaymentService(LoadUserPort loadUserPort, LoadPaymentPort loadPaymentPort) {
        this.loadUserPort = loadUserPort;
        this.loadPaymentPort = loadPaymentPort;
    }
	
	@Override
	@Transactional
	public boolean send(DoPaymentCommand command) {
		
		User user = loadUserPort.load(command.getIdUser());
		
		
		
		if(user == null){
			 throw new UserNotFoundException("No se encuentra el usuario.");
		}
		
		UserPayment payment = new UserPayment();
		payment.setIdUser(user.getIdUser());
		payment.setCardNumber(command.getCreditCard());
		payment.setPaymentDate(command.getPaymentDate());
		payment.setAmmount(command.getAmmount());
		
		loadPaymentPort.load(payment);
		
		return true;
	}

}
