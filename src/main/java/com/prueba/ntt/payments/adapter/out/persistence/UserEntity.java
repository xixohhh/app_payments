package com.prueba.ntt.payments.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@SequenceGenerator(name = "users_seq", sequenceName = "\"payments-db\".users_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
	@Column(name="id_user")
	private Long id;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="name")
	private String name;
	
	@Column(name="first_surname")
	private String firstSurname;
	
	@Column(name="second_surname")
	private String secondSurname;

}
