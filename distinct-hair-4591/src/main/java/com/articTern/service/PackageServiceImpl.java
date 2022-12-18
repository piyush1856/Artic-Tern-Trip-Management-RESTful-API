package com.articTern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.dtoes.PackageHotelDTO;
import com.articTern.enums.PackageType;
import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
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
	public TripPackage addPackage(PackageHotelDTO packageHotelDTO, String key) throws CredentialException {
		
		TripPackage mypackage = packageHotelDTO.getTripPackage();
		Set<Integer> hidSet = packageHotelDTO.getHidSet();
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		if(hidSet == null) {
			throw new HotelException("No hotel selected...");
		}
		
		List<Integer> hidList = new ArrayList<>(hidSet);
		List<Hotel> hotelList = new ArrayList<>();
		
		
		
		for(int i = 0; i < hidList.size(); i++) {	
			Optional<Hotel> op = hRepo.findById(hidList.get(i));
			
			if(op.isEmpty()) {
				throw new HotelException("Invalid Hotel ID : " +hidList.get(i));
			}
			
			hotelList.add(op.get());
			
		}
		
		
		
		for(int j = 0; j < hotelList.size(); j++) {
			mypackage.getHotelList().add(hotelList.get(j));
			hotelList.get(j).getPackageList().add(mypackage);
			
			hRepo.save(hotelList.get(j));
			
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
		
		List<Hotel> hotelList = myPackage.get().getHotelList();
		
		for(Hotel hotel : hotelList) {
			hotel.getPackageList().remove(myPackage.get());
			hRepo.save(hotel);
		}
		
		
		//Need review
		myPackage.get().getHotelList().clear();	
		pRepo.save(myPackage.get());
		
		
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


	@Override
	public String addHotelInPackage(Integer pid, Integer hid, String key)
			throws CredentialException, HotelException, PackageException {


		Optional<Hotel> existingHotel = hRepo.findById(hid);
		
		if(existingHotel.isEmpty()) {
			throw new HotelException("Invalid Hotel ID ");
		}
		
		
		Optional<TripPackage> existingPackage = pRepo.findById(pid);
		
		
		
		if(existingPackage.isEmpty()) {
			throw new PackageException("Invalid Package id");
		}
		
		for(int i = 0; i < existingPackage.get().getHotelList().size(); i++) {
			if(existingPackage.get().getHotelList().get(i).getHotelID() == hid) {
				throw new HotelException("This hotel is already present in the package");
			}
			
		}
		
		existingPackage.get().getHotelList().add(existingHotel.get());
		existingHotel.get().getPackageList().add(existingPackage.get());
		
		pRepo.save(existingPackage.get());
		
		return "Hotel "+existingHotel.get().getHotelName()+" has been added in the package "+existingPackage.get().getPackageName();
	}




	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
