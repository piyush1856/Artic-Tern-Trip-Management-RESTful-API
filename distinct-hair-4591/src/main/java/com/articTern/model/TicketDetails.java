package com.articTern.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@	Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	
	@NotNull(message = "Ticket Status Can't be null.")
	private String status;
	
	@NotNull(message = "Number of person can not be null")
	@Min(value = 1, message = "Minimum number of person should be 1")
	private Integer noOfPerson;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Route ticketRoute;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

}
