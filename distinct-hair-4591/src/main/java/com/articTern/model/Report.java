package com.articTern.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.articTern.enums.ReportType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer reportId;
	
	@NotNull(message = "Report Name Can't be null.")
	@NotBlank(message = "Report Name Can't be Blank.")
	@NotEmpty (message = "Report Name Can't be Empty.")
	private String reportName;
	
	@NotNull(message="ReportType can't be null")
	@Enumerated(EnumType.STRING)
	private ReportType reportType;
	
	@NotNull(message="Report Description can't be null")
	@NotBlank(message = "Report Description Can't be Blank.")
	@NotEmpty (message = "Report Description Can't be Empty.")
	private String reportDescription;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Admin admin;
	
	
	
	
	
	
	
	
	
	
	

}
