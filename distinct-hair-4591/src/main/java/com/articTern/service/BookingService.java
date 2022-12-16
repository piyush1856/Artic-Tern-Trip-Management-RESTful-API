package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.BookingException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Booking;


public interface BookingService {
	
	public Booking makeBooking(Booking booking, Integer packageId , String key) throws CredentialException, PackageException;
	
	public Booking cancelBooking(Integer bookingId, String key) throws BookingException, CredentialException;
	
	public Booking viewBooking(Integer bookingId, String key) throws BookingException, CredentialException;
	
	public List<Booking> viewAllBookingForCustomer(String key) throws BookingException, CredentialException;
	
	public List<Booking> viewAllBookingForAdmin(String key) throws BookingException, CredentialException;
	
}
