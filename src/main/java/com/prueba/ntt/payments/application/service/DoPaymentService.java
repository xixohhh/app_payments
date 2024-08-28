package com.prueba.ntt.payments.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.prueba.ntt.payments.application.exception.GenericAppException;
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
	
	private static final Logger logger = LoggerFactory.getLogger(DoPaymentService.class);
	
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
		payment.setDescription(command.getDescription());
		
		if(loadPaymentPort.load(payment)) {
			logger.info("Pago realizado correctamente -> [{}] ", payment);
		}else {
			logger.info("Ocurrio algun error al realizar el pago en base de datos");
			throw new GenericAppException("Ocurrio algun error al realizar el pago en base de datos",HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return true;
	}

}
