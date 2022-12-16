package com.articTern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public TripPackage addPackage(TripPackage mypackage, String key) throws PackageException {
	
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
	
}
