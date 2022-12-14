package com.articTern.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Package {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer packageId;
	
	@NotNull(message = "Package Name Can't be null.")
	@NotBlank(message = "Package Name Can't be Blank.")
	@NotEmpty (message = "Package Name Can't be Empty.")
	private String packageName;
	
	@NotNull(message = "Package Description Can't be null.")
	@NotBlank(message = "Package Description Can't be Blank.")
	@NotEmpty (message = "Package Description Can't be Empty.")
	private String packageDescription;
	
	@NotNull(message = "Package Type Can't be null.")
	@NotBlank(message = "Package Type Can't be Blank.")
	@NotEmpty (message = "Package Type Can't be Empty.")
	private String packageType;
	
	@NotNull(message = "Package Cost Can't be null.")
	private Double packageCost;
	
	
	@NotNull(message = "Payment Details Can't be null.")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "packageDetails")
	private PaymentDetails payment;
	
	
	
	
	

}
