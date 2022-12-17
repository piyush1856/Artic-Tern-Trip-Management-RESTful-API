package com.articTern.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.FeedbackException;
import com.articTern.model.Customer;
import com.articTern.model.Feedback;
import com.articTern.model.UserSession;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.FeedBackRepo;
import com.articTern.repository.SessionRepo;
@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedBackRepo fRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	
	
	@Override
	public String addFeedback(Feedback feedback, String key) throws FeedbackException {
		
		
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Please login as Customer");
		}
		
		
		Optional<Customer> opt = cRepo.findById(userSession.getUserId());
		
		
		if(opt.isPresent()) {
			
			opt.get().setFeedback(feedback);
			feedback.setCustomer(opt.get());

			fRepo.save(feedback);
			
			return "Thank you for ginving your valuable feedback.";
			
		}else {
			throw new FeedbackException("Customer not present");
			
		}		
	}



	@Override
	public String findFeedbackByCustomerId(Integer customerId, String key) throws FeedbackException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null) {
			throw new CredentialException("Please login to Continue");
		}
		
		Optional<Customer> opt= cRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			if(opt.get().getFeedback() != null) {
				
				Integer fId = opt.get().getFeedback().getFeedbackId();
				Integer fRating = opt.get().getFeedback().getRating();
				String feedback = opt.get().getFeedback().getFeedback();
				LocalDate fDate = opt.get().getFeedback().getSubmitDate();
				
				return "'Customer Id' : " + customerId+ "\n" + "'FeedbackId' : " +fId +"\n" + "'rating' : " + fRating + "\n" +"'feedBack Message' :  "+ feedback +"\n" + "'Submitted date' : " + fDate +"."; 
				
			}else {
				throw new FeedbackException("No Feedback Present with this customer ID.");
			}
		
			

		}else {
			throw new FeedbackException("No Customer Present with this customer ID.");
		}
	}



	@Override
	public String findFeedbackByfeedbackId(Integer feedbackId, String key) throws FeedbackException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		Optional<Feedback> opt=fRepo.findById(feedbackId);
		
		if(opt.isPresent()) {
			
			if(opt.get().getFeedback() != null) {
				
				Integer fId = opt.get().getFeedbackId();
				Integer fRating = opt.get().getRating();
				String feedback = opt.get().getFeedback();
				LocalDate fDate = opt.get().getSubmitDate();
				
				String customername = opt.get().getCustomer().getCustomerName();
				Integer customerId =opt.get().getCustomer().getUserId();
				String customerPhone = opt.get().getCustomer().getCustomerMobile();
				
				return "'FeedbackId' : " +fId +"\n" + "'rating' : " + fRating + "\n" +"'feedBack Message' :  "+
						feedback +"\n" + "'Submitted date' : " + fDate +"\n"+ "'customerId' :" + customerId +"\n"+ "'customeName' :" + customername +"\n"+ "'customerPhone' :" + customerPhone; 
				
			}else {
				throw new FeedbackException("No Feedback Present with this feedback ID.");
			}
		
			

		}else {
			throw new FeedbackException("No feedback Present with this feedback ID.");
		}
	}



	@Override
	public List<String> viewAllfeedback(String key) throws FeedbackException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		List<Feedback> feedbackList = fRepo.findAll();
		
		if(feedbackList.isEmpty()) {
			throw new FeedbackException("No feedback found.");
		}
		
		List<String> feedbacks = new ArrayList<>();
		
		for(Feedback f : feedbackList) {
			
			Integer fId = f.getFeedbackId();
			Integer fRating = f.getRating();
			String feedback = f.getFeedback();
			LocalDate fDate = f.getSubmitDate();
			
			String customername = f.getCustomer().getCustomerName();
			Integer customerId =f.getCustomer().getUserId();
			String customerPhone = f.getCustomer().getCustomerMobile();
			
			String fb= "'FeedbackId' : " +fId +", " + "'rating' : " + fRating + ", "  +"'feedBack Message' :  "+
					feedback +", "  + "'Submitted date' : " + fDate +", " + "'customerId' :" + customerId +", " + "'customeName' :" + customername +", " + "'customerPhone' :" + customerPhone; 
			
			feedbacks.add(fb);
			
		}
 		
		
		return feedbacks;
	}

}




























