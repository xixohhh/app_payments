package com.prueba.ntt.payments.adapter.in.web.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.prueba.ntt.payments.AppConstanst;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {
	
	@Min(value = 0,message = "Min value is {value}")
	@Max(value = 20000,message = "Max value is {value}")
	Double ammount;
	
	@NotBlank(message = "Credit card is mandatory")
	@Size(min = AppConstanst.PAYMENTS_MIN_CREDITCARD_SIZE, max = AppConstanst.PAYMENTS_MAX_CREDITCARD_SIZE,message = "Credit card lentgh is min {min} and max {max}")
	String creditCard;
	
	String description;
	
	@Schema(type = "date-time", example = "2021-01-30T08:30:00Z")
	@NotNull
	LocalDateTime paymentDate;
}
