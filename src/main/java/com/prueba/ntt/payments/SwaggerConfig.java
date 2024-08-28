package com.prueba.ntt.payments;

import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class SwaggerConfig {
	

	
	 @Bean
	 OpenAPI myOpenAPI() {
		 
	    Server devServer = new Server();
	    devServer.setUrl("http://localhost:8080");
	    devServer.setDescription("URL del servicio en desarrollo");


	    Contact contact = new Contact();
	    contact.setEmail("xxxxx@gmail.com");
	    contact.setName("xxxxx");
	    contact.setUrl("https://dominio.com");

	    Info info = new Info()
	        .title("Payments Management")
	        .version("0.0")
	        .contact(contact)
	        .description("API que permite el registro de pagos y listado de pagos.");

	    return new OpenAPI().info(info).servers(List.of(devServer));
	  }
}
