package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.CustomerException;
import com.articTern.model.Customer;

public interface CustomerService {
	
	public Customer signUpCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomerAddress(Customer customer, String key) throws CustomerException;
	
	public Customer updateCustomerName(Customer customer, String key) throws CustomerException;
	
	public Customer updateCustomerMobile(Customer customer, String key) throws CustomerException;
	
	public Customer updateCustomerEmail(Customer customer, String key) throws CustomerException;
	
	public Customer updateCustomerFeedback(Customer customer, String key) throws CustomerException;
	
	public Customer updateCustomerPassword(Customer customer, String key) throws CustomerException;
	
	public String deleteCustomer(Customer customer, String key) throws CustomerException;
	
	public Customer viewCustomer(Integer cutsomerId, String key) throws CustomerException;
	
	

}
