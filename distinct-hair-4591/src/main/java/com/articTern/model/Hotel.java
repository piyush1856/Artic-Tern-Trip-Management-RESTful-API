package com.articTern.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.articTern.enums.HotelStatus;
import com.articTern.enums.HotelType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelID;
	
	@NotNull(message = "Hotel Name Can't be null.")
	@NotBlank(message = "Hotel Name Can't be Blank.")
	@NotEmpty (message = "Hotel Name Can't be Empty.")
	@Size(min = 3, max = 20, message = "Hotel Name length should be between 3 and 20 characters.")
	private String hotelName;
	
	@NotNull(message = "Hotel Type Can't be null")
	private HotelType hotelType;
	
	@NotNull(message = "Hotel Description Can't be null.")
	@NotBlank(message = "Hotel Description Can't be Blank.")
	@NotEmpty (message = "Hotel Description Can't be Empty.")
	private String hotelDescription;
	
	
	@Embedded
	@ElementCollection
	@NotNull(message = "Hotel Address is mandatory field.")
	private List<Address> hotelAdresses = new ArrayList<>();
	
	@Min(value = 300, message = "Rent should be greater than 300")
	private Double rent;
	
	private HotelStatus hotelStatus;
	
	
	
}
