package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.ReportException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Admin;
import com.articTern.model.Report;
import com.articTern.model.UserSession;
import com.articTern.repository.AdminRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.FeedBackRepo;
import com.articTern.repository.ReportRepo;
import com.articTern.repository.SessionRepo;

@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private FeedBackRepo fRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private ReportRepo rRepo;

	@Override
	public Report addReport(Report report, String key) throws ReportException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		Optional<Admin> opt= aRepo.findById(userSession.getUserId());
		
		if(opt.isEmpty()) {
			throw new ReportException("Login as admin");
		}
		
		opt.get().getReportList().add(report);
		report.setAdmin(opt.get());
		
		return rRepo.save(report);
		
		
	}

	@Override
	public String deleteReport(Integer id, String key) throws ReportException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		Optional<Admin> opt= aRepo.findById(userSession.getUserId());
		
		Optional<Report> optR = rRepo.findById(id);
		
		if(optR.isEmpty()) {
			throw new ReportException("No Report Found");
		}
		
		List<Report> list = opt.get().getReportList();
		
		if(list.size() == 0) {
			throw new ReportException("No report found with ID : " + id);
		}
		
		for(Report r : list) {
			if(r.getReportId() == id) {
				list.remove(r);
				aRepo.save(opt.get());
				
				r.setAdmin(null);
				rRepo.save(r);
				
				break;
				
			}
		}
		
		rRepo.delete(optR.get());
		
		return "Report deleted";
		
		
	}

	@Override
	public Report searchById(Integer reportId, String key) throws ReportException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		return rRepo.findById(reportId).orElseThrow(() -> new ReportException("Report not found with this ID."));
	}

	@Override
	public List<Report> viewAllReport(String key) throws ReportException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
	 	if( rRepo.findAll().size() == 0) {
	 		throw new ReportException("No Report found");
	 	}else {
	 		return rRepo.findAll();
	 	}
	}
	
}







































