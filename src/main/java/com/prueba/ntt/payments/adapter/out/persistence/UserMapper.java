package com.prueba.ntt.payments.adapter.out.persistence;

import com.prueba.ntt.payments.domain.User;

public class UserMapper {
	
	public static User entityToDomain(UserEntity e) {
		User u = new User();
		u.setIdUser(e.getId());
		u.setUsername(e.getUsername());
		u.setName(e.getName());
		u.setFirstSurname(e.getFirstSurname());
		u.setSecondSurname(e.getSecondSurname());
		
		return u;
		
	}
	
	
	public static UserEntity domainToEntity(User e) {
		UserEntity u = new UserEntity();
		u.setId(e.getIdUser());
		u.setUsername(e.getUsername());
		u.setName(e.getName());
		u.setFirstSurname(e.getFirstSurname());
		u.setSecondSurname(e.getSecondSurname());
		
		return u;
		
	}
}
