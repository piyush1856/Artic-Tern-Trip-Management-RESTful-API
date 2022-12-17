package com.articTern.model;





import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
public class Admin extends User{
	
	@NotNull(message = "Admin Name Can't be null.")
	@NotBlank(message = "Admin Name Can't be Blank.")
	@NotEmpty (message = "Admin Name Can't be Empty.")
	@Size(min = 3, max = 20, message = "Admin Name length should be between 3 and 20 characters.")
	private String adminName;
	
	@Email
	@Column(unique = true)
	private String adminEmail;
	
	@NotNull(message = "Mobile Number cannot be null.")
	@Pattern(regexp = "[6789]{1}[0-9]{9}",message = "Invalid Mobile Number.")
	private String adminMobile;

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "admin")
	private List<Report> reportList = new ArrayList<>();

	
	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
