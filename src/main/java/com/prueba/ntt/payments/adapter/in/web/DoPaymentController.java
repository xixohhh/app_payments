package com.prueba.ntt.payments.adapter.in.web;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.ntt.payments.adapter.in.web.dto.PaymentDTO;
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
	void transfer(@PathVariable("userId") Long userId,@RequestBody Optional<PaymentDTO> dto) {

		DoPaymentCommand command = new DoPaymentCommand();
		
		command.setIdUser(userId);
		if(dto.isEmpty()) {
			//EXCEPTION
		}
		
		command.setAmmount(dto.get().getAmmount());
		command.setIdUser(userId);
		command.setCreaditCard(dto.get().getCreaditCard());
		command.setDescription(dto.get().getDescription());
		command.setPaymentDate(dto.get().getPaymentDate());
		
		doPaymentPort.send(command);
	}
}
