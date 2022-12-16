package com.articTern.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.articTern.model.Customer;
import com.articTern.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService cService;
	
	@PostMapping("/signup")
	public ResponseEntity<Customer> signUpCustomer(@Valid @RequestBody Customer customer){
		
		return new ResponseEntity<Customer>(cService.signUpCustomer(customer), HttpStatus.CREATED);
	}

	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomer(customer,key), HttpStatus.ACCEPTED);
	}
}
