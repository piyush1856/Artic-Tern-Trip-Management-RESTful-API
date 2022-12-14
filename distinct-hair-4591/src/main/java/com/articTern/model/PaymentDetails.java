package com.articTern.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.articTern.enums.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class PaymentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	private LocalDate paymentDate;
	
	private LocalTime paymentTime;
	
	@NotNull(message = "Payment Mode  Can't be null.")
	private PaymentMode paymentMode;
	
	@NotNull(message = "Payment Amount Can't be null.")
	private Double paymentAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Package packageDetails;

}
