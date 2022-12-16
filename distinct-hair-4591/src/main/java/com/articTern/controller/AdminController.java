package com.articTern.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.articTern.model.TripPackage;
import com.articTern.service.PackageService;


@RestController
@RequestMapping("/admin")
class AdminController {
	
	@Autowired
	private PackageService pService;
	
	
	@PostMapping("/addpackage")
	public ResponseEntity<TripPackage> addPackage(@Valid @RequestBody TripPackage myPackage, @RequestParam("key") String key){
		
		return new ResponseEntity<TripPackage>(pService.addPackage(myPackage, key), HttpStatus.CREATED);
		
	}
	
	
}
