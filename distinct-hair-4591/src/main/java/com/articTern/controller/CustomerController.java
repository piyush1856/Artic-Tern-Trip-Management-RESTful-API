package com.articTern.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.articTern.dtoes.FeedBackDTO;
import com.articTern.dtoes.TicketDetails;
import com.articTern.enums.PackageType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Booking;
import com.articTern.model.Bus;
import com.articTern.model.Customer;
 
import com.articTern.model.Feedback;
import com.articTern.model.TripPackage;

import com.articTern.model.Route;

import com.articTern.service.BookingService;
import com.articTern.service.BusService;
import com.articTern.service.CustomerService;
import com.articTern.service.FeedbackService;


import com.articTern.service.PackageService;
import com.articTern.service.RouteService;


@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService cService;
	
 
	@Autowired
	private FeedbackService fService;
 
	
	@Autowired
	private BookingService bService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private PackageService pService;
	
 
	@Autowired
	private RouteService rService;
	
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
	
	
	/***********************************************************************************/
	
	@PostMapping("/bookings/{pid}")
	public ResponseEntity<TicketDetails> makeBookingHandler(@Validated @RequestBody Booking booking, 
			@PathVariable("pid") Integer packageId, @RequestParam("key") String key) throws CredentialException, PackageException{
		return new ResponseEntity<TicketDetails>(bService.makeBooking(booking, packageId, key), HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/bookings/{bid}")
	public ResponseEntity<String> cancelBookingHandler(@PathVariable("bid") Integer bookingId, @RequestParam("key") String key) throws CredentialException, PackageException{
		return new ResponseEntity<String>(bService.cancelBooking(bookingId, key), HttpStatus.OK);
	}
	
	
	@GetMapping("/bookings/{bid}")
	public ResponseEntity<TicketDetails> viewBookingByBookingIdForCustomerHandler(@PathVariable("bid") Integer bookingId, @RequestParam("key") String key) throws CredentialException, PackageException{
		return new ResponseEntity<TicketDetails>(bService.viewBookingByBookingIdForCustomer(bookingId, key), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> viewAllBookingForCustomerHandler(@RequestParam("key") String key) throws CredentialException, PackageException{
		
		return new ResponseEntity<List<Booking>>(bService.viewAllBookingForCustomer(key), HttpStatus.FOUND);
	
	}
	
	
	
	@PostMapping("/feedback")
	public ResponseEntity<String> addFeedBack(@Valid @RequestBody Feedback feedback, @RequestParam String key){
		
		return new ResponseEntity<String>(fService.addFeedback(feedback,key), HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/feedback/{cid}")
	public ResponseEntity<FeedBackDTO> viewFeedbackByCustomerId(@PathVariable("cid") Integer customerId, @RequestParam("key") String key){
		
		return new ResponseEntity<FeedBackDTO>(fService.findFeedbackByCustomerId(customerId, key), HttpStatus.FOUND);
	
	}
	
	
	
	
	
	
	
	/***********************************************************************************/
	
	
	@GetMapping("/search/bus/{id}")
	public ResponseEntity<Bus> searchBusById(@PathVariable("id") Integer bId,@RequestParam("key") String key){		
		return new ResponseEntity<Bus>(busService.getBusById(bId, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/search/allbus")
	public ResponseEntity<List<Bus>> viewAllBus(@RequestParam("key") String key){		
		return new ResponseEntity<List<Bus>>(busService.getAllBus(key), HttpStatus.FOUND);		
	}
	
	
	/***********************************************************************************/
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
	
	@GetMapping("/searchpackagebytype")
	public ResponseEntity<List<TripPackage>> searchPackageByPackageTypeHandler(@RequestParam("ptype") PackageType packageType, @RequestParam("key")  String key) throws PackageException, CredentialException {		
		return new ResponseEntity<List<TripPackage>>(pService.searchPackageByPackageType(packageType, key), HttpStatus.FOUND);		
	}
	
	
	@GetMapping("/viewallpackages")
	public ResponseEntity<List<TripPackage>> viewAllPackagesHandler(@RequestParam("key") String key) throws PackageException, CredentialException {		
		return new ResponseEntity<List<TripPackage>>(pService.viewAllPackages(key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/searchpackagebyprice")
	public ResponseEntity< List<TripPackage>> searchPackageByPriceRangeHandler(@RequestParam("min") Double minPrice, 
																						@RequestParam("max") Double maxPrice, @RequestParam("key") String key) throws PackageException, CredentialException {
		return new ResponseEntity<List<TripPackage>>(pService.searchPackageByPriceRange(minPrice, maxPrice, key), HttpStatus.FOUND);
	}
	
	
	/*******************************************************************************************************************************************************/

	
	@GetMapping("/search/route/{id}")
	public ResponseEntity<Route> searchRouteById(@PathVariable("id") Integer bId,@RequestParam("key") String key){		
		return new ResponseEntity<Route>(rService.getRouteById(bId, key), HttpStatus.FOUND);		
	}
	
	
	@GetMapping("/viewroute/{src}/{des}")
	public ResponseEntity<List<Route>> getAllHotelHandler(@PathVariable("src") String src,@PathVariable("des") String des,@RequestParam("key") String key){
		
		return new ResponseEntity<List<Route>>(rService.getRouteBySourceAndDestination(src, des, key), HttpStatus.FOUND);
		
	}
	
	@GetMapping("/viewallroutes")
	public ResponseEntity<List<Route>> getAllRouteHandler(@RequestParam("key") String key){
		
		return new ResponseEntity<List<Route>>(rService.getAllRoute(key), HttpStatus.FOUND);
		
	}
	
	
	
}
