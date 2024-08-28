package com.prueba.ntt.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	Long idUser;
	private String username;
	private String name;
	private String firstSurname;
	private String secondSurname;

}
