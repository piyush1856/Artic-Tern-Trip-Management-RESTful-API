package com.articTern.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@PutMapping("/update/address")
	public ResponseEntity<Customer> updateCustomerAddress(@RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerAddress(customer,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/name")
	public ResponseEntity<Customer> updateCustomerName(@RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerName(customer,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/mobile")
	public ResponseEntity<Customer> updateCustomerMobile(@RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerMobile(customer,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/email")
	public ResponseEntity<Customer> updateCustomerEmail( @RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerEmail(customer,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/feedback")
	public ResponseEntity<Customer> updateCustomerFeedback(@RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerFeedback(customer,key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/password")
	public ResponseEntity<Customer> updateCustomerPassword(@RequestBody Customer customer,@RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.updateCustomerPassword(customer,key), HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomerHandler(@RequestBody Customer customer,@RequestParam("key") String key){
	
		
		return new ResponseEntity<String>(cService.deleteCustomer(customer, key),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id, @RequestParam("key") String key){
		
		return new ResponseEntity<Customer>(cService.viewCustomer(id, key),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
