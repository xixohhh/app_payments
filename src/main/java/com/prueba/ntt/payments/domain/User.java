package com.prueba.ntt.payments.domain;

import lombok.Data;

@Data
public class User {
	Long idUser;
	private String username;
	private String name;
	private String firstSurname;
	private String secondSurname;

}
