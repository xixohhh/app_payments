package com.prueba.ntt.payments.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.ntt.payments.application.port.in.UserPaymentsPort;
import com.prueba.ntt.payments.common.WebAdapter;
import com.prueba.ntt.payments.domain.UserPayment;

@WebAdapter
@RestController
public class ListPaymentController {

	private final UserPaymentsPort userPaymentsPort;
	
	public ListPaymentController(UserPaymentsPort userPaymentsPort) {
		this.userPaymentsPort = userPaymentsPort;
	}
	
	@GetMapping(path = "/users/{userId}/payment")
	List<UserPayment> list(@PathVariable("userId") Long userId){
		
		List<UserPayment> lista = userPaymentsPort.list(userId);
		for(UserPayment p : lista) {
			p.setCardNumber(mask(p.getCardNumber()));
		}
		return lista;
	}
	
	String mask(String card) {
		return "*****" + card.substring( card.length()-3,  card.length());
		
	}
	
}
