package com.articTern.controller;

import java.util.List;

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
import com.articTern.model.Customer;
import com.articTern.model.TravelAgency;
import com.articTern.model.TripPackage;
import com.articTern.service.FeedbackService;
import com.articTern.service.PackageService;
import com.articTern.service.TravelAgencyService;


@RestController
@RequestMapping("/admin")
class AdminController {
	
	@Autowired
	private PackageService pService;
	
	@Autowired
	private FeedbackService fservice;
	
	@Autowired
	private TravelAgencyService tService;
	
	
	@PostMapping("/addpackage")
	public ResponseEntity<TripPackage> addPackageHandler(@Valid @RequestBody TripPackage myPackage, @RequestParam("key") String key){		
		return new ResponseEntity<TripPackage>(pService.addPackage(myPackage, key), HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/deletepackage/{pid}")
	public ResponseEntity<TripPackage> deletePackageHandler(@PathVariable("pid") Integer pid, @RequestParam("key") String key){		
		return new ResponseEntity<TripPackage>(pService.deletePackage(pid, key), HttpStatus.OK);		
	}
	
	@GetMapping("/searchpackage/{pid}")
	public ResponseEntity<TripPackage> searchPackageForAdminHandler(@PathVariable("pid") Integer pid, @RequestParam("key") String key){		
		return new ResponseEntity<TripPackage>(pService.searchPackageForAdmin(pid, key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/searchpackagebytype")
	public ResponseEntity<List<TripPackage>> searchPackageByPackageTypeHandler(@RequestParam("ptype") PackageType packageType, @RequestParam("key")  String key){		
		return new ResponseEntity<List<TripPackage>>(pService.searchPackageByPackageType(packageType, key), HttpStatus.FOUND);		
	}
	
	
	@GetMapping("/viewallpackages")
	public ResponseEntity<List<TripPackage>> viewAllPackagesHandler(@RequestParam("key") String key){		
		return new ResponseEntity<List<TripPackage>>(pService.viewAllPackages(key), HttpStatus.FOUND);		
	}
	
	@GetMapping("/searchpackagebyprice")
	public ResponseEntity< List<TripPackage>> searchPackageByPriceRangeHandler(@RequestParam("min") Double minPrice, 
																						@RequestParam("max") Double maxPrice, @RequestParam("key") String key) {
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
	
	@GetMapping("/view/travelagency")
	public ResponseEntity<List<TravelAgency>> viewAllTravelAgency(@RequestParam("key") String key){		
		return new ResponseEntity<List<TravelAgency>>(tService.viewAllTravelAgency(key), HttpStatus.FOUND);		
	}
	
	
	
	
	
	
	
	
	
	
}
