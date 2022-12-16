package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.PackageType;
import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Hotel;
import com.articTern.model.TripPackage;
import com.articTern.model.UserSession;
import com.articTern.repository.HotelRepo;
import com.articTern.repository.PackageRepo;
import com.articTern.repository.SessionRepo;

@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageRepo pRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	private HotelRepo hRepo;
	
	
	@Override
	public TripPackage addPackage(TripPackage mypackage, String key) throws CredentialException {
	
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		
		
		List<Hotel> hotelList = mypackage.getHotelList();
		
		for(Hotel hotel : hotelList) {
			hotel.getPackageList().add(mypackage);
			hRepo.save(hotel);
		}
		
		return pRepo.save(mypackage);
		
	}

	
	@Override
	public TripPackage deletePackage(Integer pid, String key) throws PackageException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<TripPackage> myPackage =  pRepo.findById(pid);
		
		if(myPackage.isEmpty()) {
			throw new PackageException("Invalid package id");
		}
		
		pRepo.delete(myPackage.get());
	
		return myPackage.get();
		
	}


	@Override
	public TripPackage searchPackageForAdmin(Integer pid, String key) throws PackageException, CredentialException {
	
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<TripPackage> myPackage =  pRepo.findById(pid);
		
		if(myPackage.isEmpty()) {
			throw new PackageException("Invalid package id");
		}
		
		return myPackage.get();
	}


	@Override
	public List<TripPackage> searchPackageByPackageType(PackageType packageType, String key)
			throws PackageException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null) {
			throw new CredentialException("You are not logged in..!");
		}
		
		List<TripPackage> packages =  pRepo.findByPackageType(packageType);
		
		if(packages.isEmpty()) {
			throw new PackageException("No package found with the type " + packageType);
		}
		
		return packages;
	}

	
	@Override
	public List<TripPackage> viewAllPackages(String key) throws PackageException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null) {
			throw new CredentialException("You are not logged in..!");
		}
		
		List<TripPackage> packages =  pRepo.findAll();
		
		if(packages.isEmpty()) {
			throw new PackageException("No package found");
		}
		
		return packages;
	}




	
	
	@Override
	public List<TripPackage> searchPackageByPriceRange(Double minPrice, Double maxPrice, String key)
			throws PackageException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null) {
			throw new CredentialException("You are not logged in..!");
		}
		
		if(minPrice<= 0 || maxPrice <= 0) {
			throw new PackageException("Minimum price and maximum price should be greater than 0");
			
		} else if(minPrice  >=  maxPrice) {
			throw new PackageException("Invalid price range");
		}
		
		List<TripPackage> myPackages =  pRepo.findBypackageCostBetween(minPrice, maxPrice);
		
		if(myPackages.isEmpty()) {
			throw new PackageException("No package found in the given price range: " + minPrice + " - " + maxPrice);
		}
		
		return myPackages;
	}




	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
