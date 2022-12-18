package com.articTern.controller;

import java.util.List;

import java.util.Set;

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
import org.springframework.web.bind.annotation.RestController;


import com.articTern.dtoes.FeedBackDTO;
import com.articTern.enums.BookingType;
import com.articTern.enums.PackageType;
import com.articTern.model.Booking;
import com.articTern.model.Bus;
import com.articTern.model.Customer;
import com.articTern.model.TravelAgency;
import com.articTern.model.TripPackage;
import com.articTern.service.BookingService;
import com.articTern.service.BusService;
import com.articTern.service.FeedbackService;

import com.articTern.service.PackageService;
import com.articTern.service.ReportService;
import com.articTern.service.RouteService;
import com.articTern.service.TravelAgencyService;


import com.articTern.dtoes.PackageHotelDTO;
import com.articTern.dtoes.TicketDetails;
import com.articTern.enums.PackageType;
import com.articTern.exceptions.BookingException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.CustomerException;
import com.articTern.exceptions.HotelException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Hotel;
import com.articTern.model.Report;
import com.articTern.model.Route;
import com.articTern.model.TripPackage;
import com.articTern.service.HotelService;



@RestController
@RequestMapping("/admin")
class AdminController {
	
	@Autowired
	private PackageService pService;
	
	@Autowired
	private FeedbackService fservice;
	
	@Autowired
	private TravelAgencyService tService;
	
	@Autowired
	private BusService bService;
	
	@Autowired
	private HotelService hService;
	
	@Autowired
	private RouteService rService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private ReportService reportService;
	
	
	
	@PostMapping("/addpackage")
	public ResponseEntity<TripPackage> addPackageHandler(@Valid @RequestBody PackageHotelDTO packageHotelDTO, @RequestParam("key") String key) throws CredentialException{		
		return new ResponseEntity<TripPackage>(pService.addPackage(packageHotelDTO, key), HttpStatus.CREATED);		
	}
	
	@PostMapping("/addhotelinpackage/{pid}/{hid}")
	public ResponseEntity<String> addHotelInPackageHandler(@PathVariable("pid") Integer pid, @PathVariable("hid") Integer hid, String key) throws CredentialException, HotelException, PackageException{		
		return new ResponseEntity<String>(pService.addHotelInPackage(pid, hid, key), HttpStatus.ACCEPTED);		
	}
	
	
	@DeleteMapping("/deletepackage/{pid}")
	public ResponseEntity<TripPackage> deletePackageHandler(@PathVariable("pid") Integer pid, @RequestParam("key") String key) throws PackageException, CredentialException {		
		return new ResponseEntity<TripPackage>(pService.deletePackage(pid, key), HttpStatus.OK);		
	}
	
	@GetMapping("/searchpackage/{pid}")
	public ResponseEntity<TripPackage> searchPackageForAdminHandler(@PathVariable("pid") Integer pid, @RequestParam("key") String key) throws PackageException, CredentialException {		
		return new ResponseEntity<TripPackage>(pService.searchPackageForAdmin(pid, key), HttpStatus.FOUND);		
	}
	
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
	
	/*********************************************************************************************************************************************************************/
	@GetMapping("/feedback/{fid}")
	public ResponseEntity<FeedBackDTO> viewFeedbackByfeedbackId(@PathVariable("fid") Integer fid,@RequestParam("key") String key){		
		return new ResponseEntity<FeedBackDTO>(fservice.findFeedbackByfeedbackId(fid, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/feedback")
	public ResponseEntity<List<FeedBackDTO>> viewAllFeedback(@RequestParam("key") String key){		
		return new ResponseEntity<List<FeedBackDTO>>(fservice.viewAllfeedback(key), HttpStatus.FOUND);		
	}
	
	/*********************************************************************************************************************************************************************/
	
	@PostMapping("/travelagency")
	public ResponseEntity<TravelAgency> addTravelAgency(@Valid @RequestBody TravelAgency ta, @RequestParam("key") String key){		
		return new ResponseEntity<TravelAgency>(tService.addtravelAgency(ta, key), HttpStatus.CREATED);		
	}
	
	
	@PutMapping("/update/travelagency/travelname")
	public ResponseEntity<TravelAgency> updateTravelAgencyName(@RequestBody TravelAgency ta,@RequestParam("key") String key){
		
		return new ResponseEntity<TravelAgency>(tService.updateTravelAgencyName(ta, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/travelagency/agentname")
	public ResponseEntity<TravelAgency> updateTravelAgentName(@RequestBody TravelAgency ta,@RequestParam("key") String key){
		
		return new ResponseEntity<TravelAgency>(tService.updateTravelAgentName(ta, key), HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/update/travelagency/address")
	public ResponseEntity<TravelAgency> updateTravelAgencyAddress(@RequestBody TravelAgency ta,@RequestParam("key") String key){
		
		return new ResponseEntity<TravelAgency>(tService.updateTravelAgencyAddress(ta, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update/travelagency/mobile")
	public ResponseEntity<TravelAgency> updateTravelAgencymobile(@RequestBody TravelAgency ta,@RequestParam("key") String key){
		
		return new ResponseEntity<TravelAgency>(tService.updateTravelAgencyMobile(ta, key), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/travelagency/{id}")
	public ResponseEntity<String> deleteTravelAgency(@PathVariable("id") Integer tId,@RequestParam("key") String key){

		return new ResponseEntity<String>(tService.deleteTravelAgencyById(tId, key),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/search/travelagency/{id}")
	public ResponseEntity<TravelAgency> searchTravelAgencyById(@PathVariable("id") Integer tId,@RequestParam("key") String key){		
		return new ResponseEntity<TravelAgency>(tService.searchById(tId, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/search/alltravelagency")
	public ResponseEntity<List<TravelAgency>> viewAllTravelAgency(@RequestParam("key") String key){		
		return new ResponseEntity<List<TravelAgency>>(tService.viewAllTravelAgency(key), HttpStatus.FOUND);		
	}
	
	
	/*********************************************************************************************************************************************************************/
	
	
	@PostMapping("/bus/add/{tid}")
	public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus b, @RequestParam("key") String key,@PathVariable("tid") Integer travelId){		
		return new ResponseEntity<Bus>(bService.addBus(b, key,travelId), HttpStatus.CREATED);		
	}
	
	@PutMapping("/update/bus/type")
	public ResponseEntity<Bus> updateBusType(@RequestBody Bus b,@RequestParam("key") String key){
		
		return new ResponseEntity<Bus>(bService.updateBusType(b, key), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/bus/{id}")
	public ResponseEntity<String> deleteBus(@PathVariable("id") Integer tId,@RequestParam("key") String key){

		return new ResponseEntity<String>(bService.deleteBus(tId, key),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/search/bus/{id}")
	public ResponseEntity<Bus> searchBusById(@PathVariable("id") Integer bId,@RequestParam("key") String key){		
		return new ResponseEntity<Bus>(bService.getBusById(bId, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/search/allbus")
	public ResponseEntity<List<Bus>> viewAllBus(@RequestParam("key") String key){		
		return new ResponseEntity<List<Bus>>(bService.getAllBus(key), HttpStatus.FOUND);		
	}
	
	//can be used from both side
	@PutMapping("/update/bus/tarvelagency/{bid}/{tid}")
	public ResponseEntity<Bus> updateBusTravelsAgency(@PathVariable("bid") Integer bid,@PathVariable("tid") Integer tid,@RequestParam("key") String key){
		
		return new ResponseEntity<Bus>(bService.assignBusToTravelAgency(bid, tid, key), HttpStatus.ACCEPTED);
	}
	
	/*************************************************************************************---//Route\\---********************************************************************************/

	
	@PostMapping("/addhotel")
	public ResponseEntity<Hotel> addHotelHandler(@Valid @RequestBody Hotel hotel, @RequestParam("key") String key) throws CredentialException{
		
		return new ResponseEntity<Hotel>(hService.addHotel(hotel, key), HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/deletehotel/{hid}")
	public ResponseEntity<Hotel> deleteHotelHandler(@PathVariable("hid") Integer hid, @RequestParam("key") String key) throws HotelException, CredentialException{
		
		return new ResponseEntity<Hotel>(hService.deleteHotel(hid, key), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/viewallhotels")
	public ResponseEntity<List<Hotel>> getAllHotelHandler(@RequestParam("key") String key) throws CredentialException, HotelException{
		
		return new ResponseEntity<List<Hotel>>(hService.getAllHotel(key), HttpStatus.FOUND);
		
	}
	
	
	
	
	/*************************************************************************************---//Route\\---********************************************************************************/
	
	@PostMapping("/route/add")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route r, @RequestParam("key") String key){		
		return new ResponseEntity<Route>(rService.addRoute(r, key), HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/delete/route/{rid}")
	public ResponseEntity<String> deleteRouteHandler(@PathVariable("rid") Integer rid, @RequestParam("key") String key){
		
		return new ResponseEntity<String>(rService.deleteRoute(rid, key), HttpStatus.OK);
		
	}
	
	@GetMapping("/search/route/{id}")
	public ResponseEntity<Route> searchRouteById(@PathVariable("id") Integer bId,@RequestParam("key") String key){		
		return new ResponseEntity<Route>(rService.getRouteById(bId, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/viewroutes/{src}/{des}")
	public ResponseEntity<List<Route>> getAllHotelHandler(@PathVariable("src") String src,@PathVariable("des") String des,@RequestParam("key") String key){
		
		return new ResponseEntity<List<Route>>(rService.getRouteBySourceAndDestination(src, des, key), HttpStatus.FOUND);
		
	}
	
	@GetMapping("/viewallroutes")
	public ResponseEntity<List<Route>> getAllRouteHandler(@RequestParam("key") String key){
		
		return new ResponseEntity<List<Route>>(rService.getAllRoute(key), HttpStatus.FOUND);
		
	}
	
	@PutMapping("/route/update/fare")
	public ResponseEntity<Route> updateRouteFare(@RequestBody Route r,@RequestParam("key") String key){
		
		return new ResponseEntity<Route>(rService.updateRouteFare(r, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/route/update/arrival")
	public ResponseEntity<Route> updateRouteArrival(@RequestBody Route r,@RequestParam("key") String key){
		
		return new ResponseEntity<Route>(rService.updateRouteArrival(r, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/route/update/departure")
	public ResponseEntity<Route> updateRouteDeparture(@RequestBody Route r,@RequestParam("key") String key){
		
		return new ResponseEntity<Route>(rService.updateRouteDeparture(r, key), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/route/update/dateofjourney")
	public ResponseEntity<Route> updateRouteDateOfJourney(@RequestBody Route r,@RequestParam("key") String key){
		
		return new ResponseEntity<Route>(rService.updateRouteDateOfJourney(r, key), HttpStatus.ACCEPTED);
	}
	
	
	//can be used from both side
	@PutMapping("/update/bus/route/{bid}/{rid}")
	public ResponseEntity<Bus> updateBusRoute(@PathVariable("bid") Integer bid,@PathVariable("rid") Integer rid,@RequestParam("key") String key){
		
		return new ResponseEntity<Bus>(bService.assignBusToRoute(bid, rid, key), HttpStatus.ACCEPTED);
	}
	
	
	
	/*************************************************************************************---//Route\\---********************************************************************************/

	@PostMapping("/report/add")
	public ResponseEntity<Report> addRoute(@Valid @RequestBody Report r, @RequestParam("key") String key){		
		return new ResponseEntity<Report>(reportService.addReport(r, key), HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/report/delete/{rid}")
	public ResponseEntity<String> deleteReportById(@PathVariable("rid") Integer rid, @RequestParam("key") String key){
		
		return new ResponseEntity<String>(reportService.deleteReport(rid, key), HttpStatus.OK);
		
	}
	
	@GetMapping("/report/search/{id}")
	public ResponseEntity<Report> searchReportById(@PathVariable("id") Integer rId,@RequestParam("key") String key){		
		return new ResponseEntity<Report>(reportService.searchById(rId, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/report/viewall")
	public ResponseEntity<List<Report>> getAllReportHandler(@RequestParam("key") String key){
		
		return new ResponseEntity<List<Report>>(reportService.viewAllReport(key), HttpStatus.FOUND);
		
	}

	
	
	
	@GetMapping("/bookings/bookingid/{bid}")
	public ResponseEntity<TicketDetails> viewBookingByBookingIdForAdminHandler(@PathVariable("bid") Integer bookingId, @RequestParam("key") String key) throws BookingException, CredentialException {
		
		return new ResponseEntity<TicketDetails>(bookingService.viewBookingByBookingIdForAdmin(bookingId, key), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/bookings/customerid/{cid}")
	public ResponseEntity<List<TicketDetails>> viewBookingByCustomerIdForAdminHandler(@PathVariable("cid") Integer customerId, @RequestParam("key") String key) throws BookingException, CredentialException, CustomerException {
		
		return new ResponseEntity<List<TicketDetails>>(bookingService.viewBookingByCustomerIdForAdmin(customerId, key), HttpStatus.FOUND);
	}
	
	
	@GetMapping("/bookings")
	public ResponseEntity<List<Booking>> viewAllBookingForAdminHandler(@RequestParam("key") String key) throws BookingException, CredentialException {
		
		return new ResponseEntity<List<Booking>>(bookingService.viewAllBookingForAdmin(key), HttpStatus.FOUND);
	}
	
	@GetMapping("/bookings/bytype")
	public ResponseEntity<List<Booking>> viewBookingByBookingTypeForAdmin(@RequestParam("btype") BookingType bookingType, @RequestParam("key") String key) throws BookingException, CredentialException {
		
		return new ResponseEntity<List<Booking>>(bookingService.viewBookingByBookingTypeForAdmin(bookingType, key), HttpStatus.FOUND);
	}
	
	
	
	
}
