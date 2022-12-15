package com.articTern.model;



import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.articTern.enums.CountryEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	private String city;
	private String pincode;
	private String state;
	
	@Enumerated(EnumType.STRING)
	private CountryEnum country;
	
	
	
	
	

}
