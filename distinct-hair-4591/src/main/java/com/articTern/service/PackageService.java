package com.articTern.service;

import java.util.List;
import java.util.Set;

import com.articTern.dtoes.PackageHotelDTO;
import com.articTern.enums.PackageType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.TripPackage;

public interface PackageService {
	
	public TripPackage addPackage(PackageHotelDTO packageHotelDTO, String key) throws CredentialException;
	
	public TripPackage deletePackage(Integer pid, String key) throws PackageException, CredentialException;
	
	public TripPackage searchPackageForAdmin(Integer pid, String key) throws PackageException, CredentialException;
	
	public List<TripPackage> searchPackageByPackageType(PackageType packageType, String key) throws PackageException, CredentialException;
	
	public List<TripPackage> viewAllPackages(String key) throws PackageException, CredentialException;
	
	public List<TripPackage> searchPackageByPriceRange(Double minPrice, Double maxPrice, String key) throws PackageException, CredentialException;
	
	public String addHotelInPackage(Integer pid, Integer hid, String key) throws CredentialException, HotelException, PackageException;
	
}
