package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.HotelException;
import com.articTern.model.Hotel;

public interface HotelService {

	public Hotel addHotel(Hotel hotel, String key) throws CredentialException;
	
	public Hotel deleteHotel(Integer hid, String key) throws CredentialException, HotelException;
	
	public List<Hotel> getAllHotel(String key) throws CredentialException, HotelException;
	
}
