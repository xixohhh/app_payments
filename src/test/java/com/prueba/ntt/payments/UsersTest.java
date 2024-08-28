package com.prueba.ntt.payments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.ntt.payments.adapter.out.persistence.UserEntity;
import com.prueba.ntt.payments.adapter.out.persistence.UsersRepository;

@ExtendWith(MockitoExtension.class)
class UsersTest {
	@Mock
	private UsersRepository repository;
	
	@Test
	void getSingleUser() {
		
		UserEntity userTest = new UserEntity(0L,"test","test","test","test");

		given(repository.findById(0L)).willReturn(Optional.of(userTest));
		
		Optional<UserEntity> userToTest = repository.findById(0L);
		
		assertThat(userToTest).isNotNull();
		assertThat(userToTest.get().getName()).isEqualTo("test");
	}
	
	
}
