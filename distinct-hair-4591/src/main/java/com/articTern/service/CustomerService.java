package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.CustomerException;
import com.articTern.model.Customer;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer) throws CustomerException;
	
	public Customer deleteCustomer(Customer customer) throws CustomerException;
	
	public Customer viewCustomer(Integer cutsomerId) throws CustomerException;
	
	public List<Customer> viewAllCustomers() throws CustomerException;

}
