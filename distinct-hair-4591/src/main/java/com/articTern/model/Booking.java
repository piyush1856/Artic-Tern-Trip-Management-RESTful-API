package com.articTern.model;


import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;   
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.articTern.enums.BookingType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	
	@NotNull(message = "Booking Type Can't be null.")
	@Enumerated(EnumType.STRING)
	private BookingType bookingType;
	
	@NotNull(message = "Booking Title Can't be null.")
	@NotBlank(message = "Booking Title Can't be Blank.")
	@NotEmpty (message = "Booking Title Can't be Empty.")
	private String bookingTitle;
	
	@NotNull(message = "Number of person can not be null")
	@Min(value = 1, message = "Minimum number of person should be 1")
	private Integer noOfPerson;
	
	private LocalDateTime bookingDateTime = LocalDateTime.now();
	
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private TripPackage packageInBooking;
	
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Customer customer;
	
	
	@OneToOne(cascade = CascadeType.ALL)	
	private PaymentDetails payment;
	
	
	
	
	

}
