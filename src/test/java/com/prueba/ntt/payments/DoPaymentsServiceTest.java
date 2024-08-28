package com.prueba.ntt.payments;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.prueba.ntt.payments.application.exception.GenericAppException;
import com.prueba.ntt.payments.application.exception.UserNotFoundException;
import com.prueba.ntt.payments.application.port.in.DoPaymentCommand;
import com.prueba.ntt.payments.application.port.out.LoadPaymentPort;
import com.prueba.ntt.payments.application.port.out.LoadUserPort;
import com.prueba.ntt.payments.application.service.DoPaymentService;
import com.prueba.ntt.payments.domain.User;
import com.prueba.ntt.payments.domain.UserPayment;


@ExtendWith(MockitoExtension.class)
class DoPaymentsServiceTest {
	
	@Mock
	LoadUserPort loadUserPort;
	@Mock
	LoadPaymentPort loadPaymentPort;
	
	@InjectMocks
	DoPaymentService doPaymentsService;
	

	

	@Test
	void test_send_DoPaymentsService_UserNotFound(){

		DoPaymentCommand command = new DoPaymentCommand(10.00,"2133213312231","descrip",LocalDateTime.now(),0L);
		
		assertThrows(UserNotFoundException.class,
	            ()->{
	            	 doPaymentsService.send(command);
	            });
		
	
	}
	@Test
    void testSuccessfulPayment() throws Exception {
        // Mock user data
        User user = new User(1L, "test", "test", "test", "test");
        when(loadUserPort.load(1L)).thenReturn(user);

        // Mock payment data
        DoPaymentCommand command = new DoPaymentCommand( 100.00, "1234567890123456","sdadaa", LocalDateTime.now(), 1L);
        when(loadPaymentPort.load(any(UserPayment.class))).thenReturn(true); // Simulate successful save

        // Call the service method
        boolean result = doPaymentsService.send(command);

        // Assertions
        verify(loadUserPort, times(1)).load(1L);
        verify(loadPaymentPort, times(1)).load(any(UserPayment.class));
        assertTrue(result);
    }
	@Test
    void testPaymentSaveFailure() throws Exception {
		 User user = new User(1L, "test", "test", "test", "test");
        when(loadUserPort.load(1L)).thenReturn(user);

        DoPaymentCommand command = new DoPaymentCommand( 100.00, "1234567890123456","sdadaa", LocalDateTime.now(), 1L);
        when(loadPaymentPort.load(any(UserPayment.class))).thenReturn(false); // Simulate failed save

        assertThrows(GenericAppException.class,
                ()->{
                	doPaymentsService.send(command);
                });
    }
}
