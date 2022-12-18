package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.ReportException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Report;
import com.articTern.model.TravelAgency;

public interface ReportService {
	
	public Report addReport(Report report, String key) throws ReportException;
	
	public String deleteReport(Integer id, String key) throws ReportException;
	
	public Report searchById(Integer reportId, String key) throws ReportException;
	
	public List<Report> viewAllReport(String key) throws ReportException;

}
