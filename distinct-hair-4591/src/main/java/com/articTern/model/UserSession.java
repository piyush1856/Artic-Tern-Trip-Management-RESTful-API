package com.articTern.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.articTern.enums.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSession {
	
	@Id
	@Column(unique = true)
	@NotNull(message = "User id cannot be null")
	private Integer userId;
	
	@Column(unique = true)
	private String uuid;
	
	private LocalDateTime localDateTime;
		
	@NotNull(message = "User type cannot be null")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	
	

}
