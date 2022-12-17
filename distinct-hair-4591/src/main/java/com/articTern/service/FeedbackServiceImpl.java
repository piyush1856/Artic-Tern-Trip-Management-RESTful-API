package com.articTern.service;

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
			
//			cRepo.save(opt.get());
			fRepo.save(feedback);
			
			return "Thank you for ginving your valuable feedback.";
			
		}else {
			throw new FeedbackException("Customer not present");
			
		}
		
		
		
		
		
	}



	@Override
	public Feedback findFeedbackByCustomerId(Integer customerId, String key) throws FeedbackException {
		// TODO Auto-generated method stub
		return null;
	}

}
