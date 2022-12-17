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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.articTern.dtoes.PackageHotelDTO;
import com.articTern.enums.PackageType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Hotel;
import com.articTern.model.TripPackage;
import com.articTern.service.HotelService;
import com.articTern.service.PackageService;


@RestController
@RequestMapping("/admin")
class AdminController {
	
	@Autowired
	private PackageService pService;
	
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
