package com.articTern.service;

import java.util.List;



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
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		
		UserSession userSession = sRepo.findByUuid(key);
		
		if(userSession == null ) {
			throw new CredentialException("Please login to continue");
		}
		
		Customer existingCustomer = cRepo.findByCustomerEmail(customer.getCustomerEmail());
		//existingCustomer = new Customer(customer.getCustomerName(), customer.getCustomerEmail(), customer.getCustomerMobile(), customer.getTicketDetails(),customer.getBookingListInCustomer(), customer.getFeedback());
		
		existingCustomer.setAddresses(customer.getAddresses());
		
		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setCustomerMobile(customer.getCustomerMobile());
		existingCustomer.setCustomerEmail(customer.getCustomerEmail());
		existingCustomer.setFeedback(customer.getFeedback());
		
		if(userSession.getUserType().equals(UserType.Admin)) {
			existingCustomer.setBookingListInCustomer(customer.getBookingListInCustomer());
			existingCustomer.setTicketDetails(customer.getTicketDetails());
		}else {
			throw new CustomerException("Only Admin is authorized to update Booking or Ticket Details.");
		}
		existingCustomer.setPassword(customer.getPassword());
		
		Customer updated = cRepo.save(existingCustomer);
		return updated;
	}

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(Integer cutsomerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
