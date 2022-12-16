package com.articTern.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	
//	@NotNull(message = "Payment Details Can't be null.")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "packageDetails")
	@JsonIgnore
	private PaymentDetails payment;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "packageInTicketDetail")
	private TicketDetails ticketDetailInPackage;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "packageInBooking", cascade = CascadeType.ALL)
	private List<Booking> bookingList = new ArrayList<>();
	
	
	@ManyToMany(mappedBy = "packageList", cascade = CascadeType.ALL)
	private List<Hotel> hotelList = new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
