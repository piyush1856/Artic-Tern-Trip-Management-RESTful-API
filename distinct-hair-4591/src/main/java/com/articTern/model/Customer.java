package com.articTern.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Customer extends User{
	
	@NotNull(message = "Customer Name Can't be null.")
	@NotBlank(message = "Customer Name Can't be Blank.")
	@NotEmpty (message = "Customer Name Can't be Empty.")
	@Size(min = 3, max = 20, message = "Customer Name length should be between 3 and 20 characters.")
	private String customerName;
	
	@Email(message = "Enter Valid Email pattern or email don't exists.")
	@Column(unique = true)
	private String customerEmail;
	
	@NotNull(message = "Mobile Number cannot be null.")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Mobile Number.")
	private String customerMobile;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Booking> bookingListInCustomer = new ArrayList<>();
	
	
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "customer")
	private Feedback feedback;

	

}
