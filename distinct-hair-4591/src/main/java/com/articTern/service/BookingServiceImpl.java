package com.articTern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.dtoes.TicketDetails;
import com.articTern.enums.TicketStatus;
import com.articTern.enums.UserType;
import com.articTern.exceptions.BookingException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.PackageException;
import com.articTern.model.Booking;
import com.articTern.model.Customer;
import com.articTern.model.PaymentDetails;
import com.articTern.model.TripPackage;
import com.articTern.model.UserSession;
import com.articTern.repository.BookingRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.PackageRepo;
import com.articTern.repository.SessionRepo;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private PackageRepo pRepo;
	
	@Autowired
	private BookingRepo bRepo;

	
	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Override
	public TicketDetails makeBooking(Booking booking, Integer packageId, String key) throws CredentialException, PackageException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Kindly login as Customer");
		}
		
		Optional<TripPackage> myPackage = pRepo.findById(packageId);
		
		if(myPackage.isEmpty()) {
			throw new PackageException("Invalid Package ID ");
		}
		
//		Associating Booking and package
		
		booking.setPackageInBooking(myPackage.get());		
		myPackage.get().getBookingList().add(booking);
		
		
		
//		Associating customer and booking
		
		Customer customer = cRepo.findById(usersession.getUserId()).get();		
		booking.setCustomer(customer);		
		customer.getBookingListInCustomer().add(booking);
		
		Booking savedBookingDetails = bRepo.save(booking);
		
		TicketDetails tc = new TicketDetails();
		
		tc.setTicketId(savedBookingDetails.getBookingId());
		tc.setBookingDateTime(savedBookingDetails.getBookingDateTime());
		tc.setBookingType(savedBookingDetails.getBookingType());
		tc.setCustomerEmail(savedBookingDetails.getCustomer().getCustomerEmail());
		tc.setCustomerMobile(savedBookingDetails.getCustomer().getCustomerMobile());
		tc.setCustomerName(savedBookingDetails.getCustomer().getCustomerName());
		tc.setNoOfPerson(savedBookingDetails.getNoOfPerson());
		tc.setStatus(TicketStatus.CONFIRMED);
		tc.setPayment(savedBookingDetails.getPayment());
		
		return tc;
		
	}

	@Override
	public String cancelBooking(Integer bookingId, String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Kindly login as Customer");
		}
		
		Optional<Booking> existingBooking = bRepo.findById(bookingId);
		
		if(existingBooking.isEmpty()) {
			throw new BookingException("Invalid Booking ID");
		}
		
		existingBooking.get().setPackageInBooking(null);
		existingBooking.get().getPackageInBooking().getBookingList().remove(existingBooking.get());
		
		bRepo.save(existingBooking.get());
		
		bRepo.delete(existingBooking.get());
		
		return "Ticket with Booking Id :  "+bookingId +" has been cancelled successfully..";
	}

	@Override
	public Booking viewBooking(Integer bookingId, String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Kindly login as Customer");
		}
		
		Optional<Booking> existingBooking = bRepo.findById(bookingId);
		
		if(existingBooking.isEmpty()) {
			throw new BookingException("Invalid Booking ID");
		}
		
		
		return existingBooking.get();
		
		
	}

	@Override
	public List<Booking> viewAllBookingForCustomer(String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Kindly login as Customer");
		}
		
		
		List<Booking> allBookingDetails = bRepo.findAll();
		
		
		
		if(allBookingDetails.isEmpty()) {
			throw new BookingException("No booking found");
		}
		
		List<Booking> bookingListForCustomer = new ArrayList<>();
		
		for(int i = 0; i < allBookingDetails.size(); i++) {
			if(allBookingDetails.get(i).getCustomer().getUserId() == usersession.getUserId()) {
				bookingListForCustomer.add(allBookingDetails.get(i));
			}
		}
		if(bookingListForCustomer.isEmpty()) {
			throw new BookingException("No booking found");
		}
		
		return bookingListForCustomer;
	}

	@Override
	public List<Booking> viewAllBookingForAdmin(String key) throws BookingException, CredentialException {
		
		

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		
		List<Booking> allBookingDetails = bRepo.findAll();
		
		if(allBookingDetails.isEmpty()) {
			throw new BookingException("No booking found");
		}
		
		return allBookingDetails;
		
		
	}
	
	
	
	
	
}
