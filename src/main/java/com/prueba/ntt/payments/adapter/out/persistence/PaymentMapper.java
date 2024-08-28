package com.prueba.ntt.payments.adapter.out.persistence;

import com.prueba.ntt.payments.domain.UserPayment;

public class PaymentMapper {
	
	private PaymentMapper() {}
	
	public static UserPayment entityToDomain(PaymentEntity e) {
		
		UserPayment u =  new UserPayment();
		
		u.setId(e.getId());
		u.setIdUser(e.getIdUser());
		u.setAmmount(e.getAmmount());
		u.setCardNumber(e.getCardNumber());
		u.setPaymentDate(e.getPaymentDate());
		u.setDescription(e.getDescription());
		return u;
	}
	
	public static PaymentEntity domainToEntity(UserPayment e) {
		
		PaymentEntity u =  new PaymentEntity();
		
		u.setId(e.getId());
		u.setIdUser(e.getIdUser());
		u.setAmmount(e.getAmmount());
		u.setCardNumber(e.getCardNumber());
		u.setPaymentDate(e.getPaymentDate());
		u.setDescription(e.getDescription());
		return u;
	}
}
