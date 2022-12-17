package com.articTern.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@NotNull(message = "Description Can't be null.")
	@NotBlank(message = "Description Can't be Blank.")
	@NotEmpty (message = "Description Can't be Empty.")
	private String description;
	
	@NotNull(message = "Booking Title Can't be null.")
	@NotBlank(message = "Booking Title Can't be Blank.")
	@NotEmpty (message = "Booking Title Can't be Empty.")
	private String bookingTitle;
	
	private LocalDate bookingDate = LocalDate.now();
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private TripPackage packageInBooking;
	
	
	@ManyToOne( cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Customer customer;
	
	
	
	

}
