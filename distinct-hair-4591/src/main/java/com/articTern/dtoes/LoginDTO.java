package com.articTern.dtoes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.articTern.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	@NotNull(message = "Email Id Can't be null.")
	@NotBlank(message = "Email Id Can't be Blank.")
	@NotEmpty (message = "Email Id Can't be Empty.")
	@Email
	private String emailId;
	
	@NotNull(message = "Password Can't be null.")
	@NotBlank(message = "Password Can't be Blank.")
	@NotEmpty (message = "Password Can't be Empty.")
	private String password;
	
	@NotNull(message = "User Type Can't Be Null")
	private UserType userType;
	
	
	
	

}
