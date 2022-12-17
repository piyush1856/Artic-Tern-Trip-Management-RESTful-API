package com.articTern.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.articTern.enums.BusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  //MH 05 DL 9023
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer busId;
	
	@NotNull(message = "Bus Type Can't be null.")
	@Enumerated(EnumType.STRING)
	private BusType busType;
	
	@NotNull(message = "Bus Number Can't be null.")
 	@Pattern(regexp = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$",message = "Invalid Bus Number.")  //MH-05-DL-9023
 	@Column(unique = true)
	private String busNumber;
	
	@NotNull(message = "Bus Capacity Can't be null.")
	@Min(value = 1,message = "Bus Capacity must be in range of 1 to 30.")
	@Max(value = 30, message = "Bus Capacity must be in range of 1 to 30.")
	private Integer busCapacity;
	
 
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "travelId")
	private TravelAgency travelAgency;
	
	 
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "routeId")
	private Route busRoute;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
