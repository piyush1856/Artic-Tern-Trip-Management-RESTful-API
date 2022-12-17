package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.model.Hotel;
import com.articTern.model.UserSession;
import com.articTern.repository.HotelRepo;
import com.articTern.repository.SessionRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	private HotelRepo hRepo;
	
	
	@Override
	public Hotel addHotel(Hotel hotel, String key) throws CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		return hRepo.save(hotel);
		
	}

	@Override
	public Hotel deleteHotel(Integer hid, String key) throws CredentialException, HotelException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Hotel> existingHotel = hRepo.findById(hid);
	
		if(existingHotel.isEmpty()) {
			throw new HotelException("Invalid Hotel ID");
		}
		
		hRepo.delete(existingHotel.get());
		
		return existingHotel.get();
	}

	@Override
	public List<Hotel> getAllHotel(String key) throws CredentialException, HotelException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		List<Hotel> hotelList = hRepo.findAll();
		
		if(hotelList.isEmpty()) {
			throw new HotelException("No Hotel Found");
		}
		
		
		return hotelList;
		
	}

}
