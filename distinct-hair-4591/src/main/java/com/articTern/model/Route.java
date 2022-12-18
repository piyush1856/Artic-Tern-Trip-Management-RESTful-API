package com.articTern.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routeId;
	
	@NotNull(message = "'Route From' Can't be null.")
	@NotBlank(message = "'Route From' Can't be Blank.")
	@NotEmpty (message = "'Route From' Can't be Empty.")
	private String routeFrom;
	
	@NotNull(message = "'Route To' Can't be null.")
	@NotBlank(message = "'Route To' Can't be Blank.")
	@NotEmpty (message = "'Route To' Can't be Empty.")
	private String routeTo;
	
	@NotNull(message = "Departure time is Mandatory.")
	private LocalTime departureTime;
	
	
	@NotNull(message = "Arrival time is Mandatory.")
	private LocalTime arrivalTime;
	

	@NotNull(message = "Date of Journey is Mandatory.")
	private LocalDate dateOfJourney;
	
	@NotNull(message = "Pickup Point Can't be null.")
	@NotBlank(message = "Pickup Point Can't be Blank.")
	@NotEmpty (message = "Pickup Point Can't be Empty.")
	private String pickupPoint;
	
	@NotNull(message = "Fare Can't be null.")
	private Double fare;
	
	@JsonIgnore
	@OneToMany(mappedBy = "busRoute", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Bus> routeBusList = new ArrayList<>();
	
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REFRESH}, mappedBy = "routesInPackage")
	private List<TripPackage> packagesInRoute = new ArrayList<>();
	
	
	
	

}
