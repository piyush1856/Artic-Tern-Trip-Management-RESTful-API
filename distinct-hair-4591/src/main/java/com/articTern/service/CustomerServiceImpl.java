package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.CustomerException;
import com.articTern.model.Customer;
import com.articTern.model.UserSession;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
 
	@Override
	public Customer signUpCustomer(Customer customer) throws CustomerException {
		 Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		 if(existingCustomer!=null) {
			 throw new CustomerException("Customer already exist with emailId : "+customer.getCustomerEmail());
			 
		 }
		 return cRepo.save(customer);
		 
		  
	}

	
	
	@Override
	public Customer updateCustomerAddress(Customer customer, String key) throws CustomerException {
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		existingCustomer.setAddresses(customer.getAddresses());
		
		Customer updated = cRepo.save(existingCustomer);
		
		return updated;
		
	}

	@Override
	public Customer updateCustomerName(Customer customer, String key) throws CustomerException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		existingCustomer.setCustomerName(customer.getCustomerName());
		
		Customer updated = cRepo.save(existingCustomer);
		
		return updated;
	}

	@Override
	public Customer updateCustomerMobile(Customer customer, String key) throws CustomerException {
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		existingCustomer.setCustomerMobile(customer.getCustomerMobile());
		
		Customer updated = cRepo.save(existingCustomer);
		
		return updated;
	}

	@Override
	public Customer updateCustomerEmail(Customer customer, String key) throws CustomerException {
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Optional<Customer> opt= cRepo.findById(userSession.getUserId());
		
		if(opt.isPresent()) {
			Customer existingCustomer = opt.get();
			
			existingCustomer.setCustomerEmail(customer.getCustomerEmail());
			
			Customer updated = cRepo.save(existingCustomer);
				
			return updated;
		}else {
			throw new CustomerException("User not logged in with id : " + userSession.getUserId() + " or email not found. ");
		}	
	
	}

	@Override
	public Customer updateCustomerFeedback(Customer customer, String key) throws CustomerException {
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		existingCustomer.setFeedback(customer.getFeedback());
		
		Customer updated = cRepo.save(existingCustomer);
		
		return updated;
	}

	@Override
	public Customer updateCustomerPassword(Customer customer, String key) throws CustomerException {
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		
		existingCustomer.setPassword(customer.getPassword());
		
		Customer updated = cRepo.save(existingCustomer);
		
		return updated;
	}

	@Override
	public String deleteCustomer(Customer customer, String key) throws CustomerException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Optional<Customer> opt= cRepo.findById(userSession.getUserId());
		
		if(opt.isPresent() && customer.getPassword().equals(opt.get().getPassword())) {
			cRepo.delete(opt.get());
			sRepo.delete(userSession);
			return  "This Account deleted succesfully - "+opt.get().toString();
			
		}else {
			throw new CredentialException("Enter correct password to delete your account or user doesn't exist");
		}
		
		
	}

	@Override
	public Customer viewCustomer(Integer cutsomerId,String key) throws CustomerException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue or enter correct key ..");
		}
		
		if(cutsomerId == userSession.getUserId()) {
			
			Optional<Customer> opt= cRepo.findById(userSession.getUserId());
			
			return opt.get();
		}else {
			throw new CustomerException(" You can only view your profile by entering your userId .. ");
		}
		
		
	}


	

	

}
