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
import com.articTern.enums.PackageType;
import com.articTern.model.Bus;
import com.articTern.model.Customer;
import com.articTern.model.TravelAgency;
import com.articTern.model.TripPackage;
import com.articTern.service.BusService;
import com.articTern.service.FeedbackService;

import com.articTern.service.PackageService;
import com.articTern.service.TravelAgencyService;


import com.articTern.dtoes.PackageHotelDTO;
import com.articTern.enums.PackageType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Hotel;
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
	
	@PostMapping("/addpackage")
	public ResponseEntity<TripPackage> addPackageHandler(@Valid @RequestBody PackageHotelDTO packageHotelDTO, @RequestParam("key") String key) throws CredentialException{		
		return new ResponseEntity<TripPackage>(pService.addPackage(packageHotelDTO, key), HttpStatus.CREATED);		
	}
	
	@PostMapping("/addhotelinpackage/{pid}/{hid}")
	public ResponseEntity<String> addHotelInPackage(@PathVariable("pid") Integer pid, @PathVariable("hid") Integer hid, String key) throws CredentialException, HotelException, PackageException{		
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
	
	@DeleteMapping("/update/travelagency/delete/{id}")
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
	
	
	@PostMapping("/bus/add")
	public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus b, @RequestParam("key") String key){		
		return new ResponseEntity<Bus>(bService.addBus(b, key), HttpStatus.CREATED);		
	}
	
	@PutMapping("/update/bus/type")
	public ResponseEntity<Bus> updateBusType(@RequestBody Bus b,@RequestParam("key") String key){
		
		return new ResponseEntity<Bus>(bService.updateBusType(b, key), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/update/bus/delete/{id}")
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
	
	
	
}
