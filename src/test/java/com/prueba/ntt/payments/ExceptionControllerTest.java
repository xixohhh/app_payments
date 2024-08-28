package com.prueba.ntt.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.prueba.ntt.payments.application.exception.ErrorResponse;
import com.prueba.ntt.payments.application.exception.ExceptionController;
import com.prueba.ntt.payments.application.exception.GenericAppException;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
class ExceptionControllerTest {

    @InjectMocks
    ExceptionController exceptionController; 
    
    @Mock
    HttpServletRequest request;

    @Test
    void testGenericAppExceptionHandler() {
        String errorMessage = "An internal application error occurred.";
        int httpCode = 500;
        GenericAppException ex = new GenericAppException(errorMessage, httpCode);

        
        ResponseEntity<ErrorResponse> response = exceptionController.genericAppExceptionHandler(ex, request);
       
        assertEquals(HttpStatus.valueOf(httpCode), response.getStatusCode());
        
    }

}
