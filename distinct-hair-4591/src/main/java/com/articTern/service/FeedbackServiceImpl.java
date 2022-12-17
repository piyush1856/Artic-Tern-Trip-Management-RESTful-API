package com.articTern.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.dtoes.FeedBackDTO;
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
			//cRepo.save(feedback)
			fRepo.save(feedback);
			
			return "Thank you for ginving your valuable feedback.";
			
		}else {
			throw new FeedbackException("Customer not present");
			
		}		
	}



	@Override
	public FeedBackDTO findFeedbackByCustomerId(Integer customerId, String key) throws FeedbackException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null) {
			throw new CredentialException("Please login to Continue");
		}
		
		Optional<Customer> opt= cRepo.findById(customerId);
		
		if(opt.isPresent()) {
			
			if(opt.get().getFeedback() != null) {
				
				FeedBackDTO fdo = new FeedBackDTO();
				
				
				fdo.setFid(opt.get().getFeedback().getFeedbackId()); 
				fdo.setRating(opt.get().getFeedback().getRating()); 
				fdo.setFeedback(opt.get().getFeedback().getFeedback()); 
				fdo.setDate(opt.get().getFeedback().getSubmitDate());
				fdo.setCid(opt.get().getUserId());
				fdo.setCname(opt.get().getCustomerName());
				fdo.setCphone(opt.get().getCustomerMobile());
				
				return fdo; 
				
			}else {
				throw new FeedbackException("No Feedback Present with this customer ID.");
			}
		
			

		}else {
			throw new FeedbackException("No Customer Present with this customer ID.");
		}
	}



	@Override
	public FeedBackDTO findFeedbackByfeedbackId(Integer feedbackId, String key) throws FeedbackException {
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		Optional<Feedback> opt=fRepo.findById(feedbackId);
		
		if(opt.isPresent()) {
			
			if(opt.get().getFeedback() != null) {
				
				FeedBackDTO fdo = new FeedBackDTO();
				Customer c = opt.get().getCustomer();
				
				fdo.setFid(opt.get().getFeedbackId()); 
				fdo.setRating(opt.get().getRating()); 
				fdo.setFeedback(opt.get().getFeedback()); 
				fdo.setDate(opt.get().getSubmitDate());
				fdo.setCid(c.getUserId());
				fdo.setCname(c.getCustomerName());
				fdo.setCphone(c.getCustomerMobile());
				
				return fdo;
				
			}else {
				throw new FeedbackException("No Feedback Present with this feedback ID.");
			}
		
			

		}else {
			throw new FeedbackException("No feedback Present with this feedback ID.");
		}
	}



	@Override
	public List<FeedBackDTO> viewAllfeedback(String key) throws FeedbackException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		
		if(userSession == null || userSession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Please login as Admin");
		}
		
		List<Feedback> feedbackList = fRepo.findAll();
		
		if(feedbackList.isEmpty()) {
			throw new FeedbackException("No feedback found.");
		}
		
		List<FeedBackDTO> feedbacks = new ArrayList<>();
		
		for(Feedback f : feedbackList) {
			
			FeedBackDTO fdo = new FeedBackDTO();
			

			fdo.setFid(f.getFeedbackId()); 
			fdo.setRating(f.getRating()); 
			fdo.setFeedback(f.getFeedback()); 
			fdo.setDate(f.getSubmitDate());
			
			fdo.setCid(f.getCustomer().getUserId());
			fdo.setCname(f.getCustomer().getCustomerName());
			fdo.setCphone(f.getCustomer().getCustomerMobile());
			
			
			feedbacks.add(fdo);
			
		}
 		
		
		return feedbacks;
	}

}




























