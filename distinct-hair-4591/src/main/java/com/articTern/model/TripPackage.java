package com.articTern.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.articTern.enums.PackageType;
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
public class TripPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer packageId;
	
	@NotNull(message = "Package Name Can't be null.")
	@NotBlank(message = "Package Name Can't be Blank.")
	@NotEmpty (message = "Package Name Can't be Empty.")
	@Column(unique = true)
	private String packageName;
	
	@NotNull(message = "Package Description Can't be null.")
	@NotBlank(message = "Package Description Can't be Blank.")
	@NotEmpty (message = "Package Description Can't be Empty.")
	private String packageDescription;
	
	@NotNull(message = "Package Type Can't be null.")
	@Enumerated(EnumType.STRING)
	private PackageType packageType;
	
	@NotNull(message = "Package Cost Can't be null.")
	private Double packageCost;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "packageInBooking", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Booking> bookingList = new ArrayList<>();
	
	
	@ManyToMany(mappedBy = "packageList", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Hotel> hotelList = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REFRESH})
	private List<Route> routesInPackage = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
