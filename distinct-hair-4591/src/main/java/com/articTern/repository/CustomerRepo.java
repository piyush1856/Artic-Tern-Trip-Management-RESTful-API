package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public Customer findByCustomerEmail(String customerEmail);
	
	
}
