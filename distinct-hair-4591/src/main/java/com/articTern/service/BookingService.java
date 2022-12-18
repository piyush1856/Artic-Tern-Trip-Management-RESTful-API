package com.articTern.service;

import java.util.List;

import com.articTern.dtoes.TicketDetails;
import com.articTern.enums.BookingType;
import com.articTern.exceptions.BookingException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.CustomerException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Booking;


public interface BookingService {
	
	public TicketDetails makeBooking(Booking booking, Integer packageId , String key) throws CredentialException, PackageException;
	
	public String cancelBooking(Integer bookingId, String key) throws BookingException, CredentialException;
	
	public TicketDetails viewBookingByBookingIdForCustomer(Integer bookingId, String key) throws BookingException, CredentialException;
	
	public TicketDetails viewBookingByBookingIdForAdmin(Integer bookingId, String key) throws BookingException, CredentialException;
	
	public List<TicketDetails> viewBookingByCustomerIdForAdmin(Integer customerId, String key) throws BookingException, CredentialException, CustomerException;
	
	public List<Booking> viewAllBookingForCustomer(String key) throws BookingException, CredentialException;
	
	public List<Booking> viewAllBookingForAdmin(String key) throws BookingException, CredentialException;
	
	public List<Booking> viewBookingByBookingTypeForAdmin(BookingType bookingType, String key) throws BookingException, CredentialException;
	
}
