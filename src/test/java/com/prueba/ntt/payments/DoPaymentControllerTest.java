package com.prueba.ntt.payments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.prueba.ntt.payments.adapter.in.web.DoPaymentController;
import com.prueba.ntt.payments.adapter.in.web.dto.PaymentDTO;
import com.prueba.ntt.payments.application.port.in.DoPaymentCommand;
import com.prueba.ntt.payments.application.port.in.DoPaymentPort;

@SpringBootTest
class DoPaymentControllerTest {
	

	protected MockMvc mvc;

	@Mock
	DoPaymentPort doPaymentPort;
	
	@InjectMocks
	DoPaymentController doPaymentController;

	@Autowired
	WebApplicationContext webApplicationContext;

	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	void test_doPayment() throws Exception{
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		DoPaymentCommand command = new DoPaymentCommand(10.0, "2133113131313", "seasdadas", LocalDateTime.now(),0L);
		
		PaymentDTO dto =  new PaymentDTO(10.0, "2133113131313", "seasdadas", LocalDateTime.now());
		
		String url = "/users/0/payment";
		
		when(doPaymentPort.send(command)).thenReturn(true);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(url).content(objectMapper.writeValueAsString(dto))).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus());
	}
	
//	@Test
//	void test_doPayment_max_ammount_error() throws Exception{
//		
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		
//		DoPaymentCommand command = new DoPaymentCommand(28000.0, "213311313sadasdadaddadasdaadsa1313", "seasdadas", LocalDateTime.now(),0L);
//		
//		PaymentDTO dto =  new PaymentDTO(28000.0, "213311313sadasdadaddadasdadsadadadsa1313", "seasdadas", LocalDateTime.now());
//		
//		
////		when(doPaymentPort.send(command)).thenReturn(true);
//		
//		doPaymentController.transfer(0L, Optional.of(dto));
//		
//		//doThrow(new MethodArgumentNotValidException(null, null)).when(null);
//		//doNothing().when(doPaymentController).transfer(null,Optional.of(dto));
//		
//		verify(doPaymentPort).send(command);
//
////		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/users/{userId}/payment",0L)
////				.contentType(MediaType.APPLICATION_JSON_VALUE)
////				.content(objectMapper.writeValueAsString(dto))).andReturn();
////		
////		assertNotEquals(-1, mvcResult.getResponse().getContentAsString().indexOf("[Max value is 20000]"));
//	}
    @Test
    void testTransfer_Exito() {
        // Arrange
        Long userId = 1L;
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmmount(100.0);
        paymentDTO.setCreditCard("1234-5678-9012-3456");
        paymentDTO.setDescription("Pago de ejemplo");
        paymentDTO.setPaymentDate(LocalDateTime.now());
        
        Optional<PaymentDTO> dto = Optional.of(paymentDTO);

        // Act
        doPaymentController.transfer(userId, dto);

        // Assert
        // Verifica que el método se ha llamado una vez con el comando correcto
        verify(doPaymentPort, times(1)).send(argThat(command -> 
            command.getIdUser().equals(userId) &&
            command.getAmmount().equals(paymentDTO.getAmmount()) &&
            command.getCreditCard().equals(paymentDTO.getCreditCard()) &&
            command.getDescription().equals(paymentDTO.getDescription())
        ));
    }
}
