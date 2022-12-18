package com.articTern.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelAgency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer travelId;
	
	@NotNull(message = "Travels Name Can't be null.")
	@NotBlank(message = "Travels Name Can't be Blank.")
	@NotEmpty (message = "Travels Name Can't be Empty.")
	@Size(min = 3, max = 20, message = "Travels Name length should be between 3 and 20 characters.")
	private String travelsName;
	
	@NotNull(message = "Agent Name Can't be null.")
	@NotBlank(message = "Agent Name Can't be Blank.")
	@NotEmpty (message = "Agent Name Can't be Empty.")
	@Size(min = 3, max = 20, message = "Agent Name length should be between 3 and 20 characters.")
	private String agentName;
	
	@Embedded
	@ElementCollection
	@JoinTable(name = "address_travels", joinColumns = @JoinColumn(name = "travelId"))
	@NotNull(message = "Address is mandatory field.")
	private List<Address> travelAdresses = new ArrayList<>();
	
	@NotNull(message = "Mobile Number cannot be null.")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Mobile Number.")
	private String travelMobile;
	
	@JsonIgnore
	@OneToMany(mappedBy = "travelAgency", cascade = /*{CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}*/ CascadeType.ALL)
	private List<Bus> busList = new ArrayList<>();

	
	
	
	
	
	
	
}
