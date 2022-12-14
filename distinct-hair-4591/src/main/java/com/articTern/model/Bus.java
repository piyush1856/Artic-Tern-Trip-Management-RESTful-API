package com.articTern.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.hibernate.validator.constraints.Range;

import com.articTern.enums.BusType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  //JH-17B 3232
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	@NotNull(message = "Bus Type Can't be null.")
	@NotBlank(message = "Bus Type Can't be Blank.")
	@NotEmpty (message = "Bus Type Can't be Empty.")
	private BusType busType;
	
	@NotNull(message = "Bus Number Can't be null.")
	@NotBlank(message = "Bus Number Can't be Blank.")
	@NotEmpty (message = "Bus Number Can't be Empty.")
	//@Pattern(regexp = "[A-Z]{2}[0-9]{2}[A-Z]{1}[0-9]{4}",message = "Invalid Mobile Number.")
	@Column(unique = true)
	private String busNumber;
	
	@NotNull(message = "Bus Capacity Can't be null.")
	@Range(min = 1,max = 30,message = "Bus Capacity must be in range of 1 to 30.")
	private Integer busCapacity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "travelId")
	private TravelAgency travelAgency;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "routeId")
	private Route busRoute;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
