package com.articTern.service;

import java.util.List;

import com.articTern.enums.PackageType;
import com.articTern.exceptions.PackageException;
import com.articTern.model.TripPackage;

public interface PackageService {
	
	public TripPackage addPackage(TripPackage mypackage, String key) throws PackageException;
	
	public TripPackage deletePackage(Integer pid, String key) throws PackageException;
	
	public TripPackage searchPackageForAdmin(Integer pid, String key) throws PackageException;
	
	public List<TripPackage> searchPackageByPackageType(PackageType packageType, String key) throws PackageException;
	
	public List<TripPackage> viewAllPackages(String key) throws PackageException;
	
	public List<TripPackage> searchPackageByPriceRange(Double minPrice, Double maxPrice, String key) throws PackageException;
	
}
