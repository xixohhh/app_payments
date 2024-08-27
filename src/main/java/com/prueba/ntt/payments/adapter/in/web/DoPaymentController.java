package com.prueba.ntt.payments.adapter.in.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.ntt.payments.AppConstanst;
import com.prueba.ntt.payments.adapter.in.web.dto.PaymentDTO;
import com.prueba.ntt.payments.application.exception.BodyRequestMissingException;
import com.prueba.ntt.payments.application.exception.PaymentAmmountInvalidException;
import com.prueba.ntt.payments.application.port.in.DoPaymentCommand;
import com.prueba.ntt.payments.application.port.in.DoPaymentPort;
import com.prueba.ntt.payments.common.WebAdapter;

@WebAdapter
@RestController
public class DoPaymentController {

	private final DoPaymentPort doPaymentPort;

	public DoPaymentController(DoPaymentPort doPaymentPort) {
		this.doPaymentPort = doPaymentPort;
	}

	@PostMapping(path = "/users/{userId}/payment")
	void transfer(
			@PathVariable("userId") Long userId,
			@RequestBody Optional<PaymentDTO> dto
			) {

		DoPaymentCommand command = new DoPaymentCommand();
		
		command.setIdUser(userId);
		
		if(userId == null) {
			throw new BodyRequestMissingException("Id usuario no presente en peticion de pago.");
		}
		
		if(!dto.isPresent()) {
			throw new BodyRequestMissingException("No se encuentra bodyRequest al realizar el pago.");
		}
		if(dto.get().getCreditCard() == null ) {
			
			throw new BodyRequestMissingException("No se encuentra el numero de tarjeta.");
		}
		if(dto.get().getAmmount() == null && 
				dto.get().getAmmount().compareTo(AppConstanst.PAYMENTS_MAX_AMMOUNT_VALUE) > 1  
				|| dto.get().getAmmount().compareTo( AppConstanst.PAYMENTS_MIN_AMMOUNT_VALUE) < 0 ) {
			
			throw new PaymentAmmountInvalidException("Valor incorrecto de la propiedad ammount.");
		}
		
		command.setAmmount(dto.get().getAmmount());
		command.setIdUser(userId);
		command.setCreditCard(dto.get().getCreditCard());
		command.setDescription(dto.get().getDescription());
		command.setPaymentDate(dto.get().getPaymentDate());
		
		doPaymentPort.send(command);
	}
}
