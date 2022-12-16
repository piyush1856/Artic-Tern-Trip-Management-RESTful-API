package com.articTern.service;

import com.articTern.exceptions.PackageException;
import com.articTern.model.TripPackage;

public interface PackageService {
	
	public TripPackage addPackage(TripPackage mypackage, String key) throws PackageException;
	
	
}
