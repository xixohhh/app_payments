package com.prueba.ntt.payments.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	@GeneratedValue
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
