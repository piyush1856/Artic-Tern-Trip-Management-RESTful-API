package com.articTern.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	
	@NotNull(message = "Booking Type Can't be null.")
	@NotBlank(message = "Booking Type Can't be Blank.")
	@NotEmpty (message = "Booking Type Can't be Empty.")
	private String bookingType;
	
	@NotNull(message = "Description Can't be null.")
	@NotBlank(message = "Description Can't be Blank.")
	@NotEmpty (message = "Description Can't be Empty.")
	private String description;
	
	@NotNull(message = "Booking Title Can't be null.")
	@NotBlank(message = "Booking Title Can't be Blank.")
	@NotEmpty (message = "Booking Title Can't be Empty.")
	private String bookingTitle;
	
	@NotNull(message = "Booking Date Can't Be Null.")
	private LocalDate bookingDate;

}
