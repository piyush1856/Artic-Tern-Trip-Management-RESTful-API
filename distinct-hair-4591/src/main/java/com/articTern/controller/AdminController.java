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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.articTern.enums.PackageType;
import com.articTern.model.TripPackage;
import com.articTern.service.PackageService;


@RestController
@RequestMapping("/admin")
class AdminController {
	
	@Autowired
	private PackageService pService;
	
	
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
	
	
	
	
	
	
}
