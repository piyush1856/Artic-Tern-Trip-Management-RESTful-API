package com.articTern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.dtoes.TicketDetails;
import com.articTern.enums.BookingType;
import com.articTern.enums.TicketStatus;
import com.articTern.enums.UserType;
import com.articTern.exceptions.BookingException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.CustomerException;
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
		tc.setPackageName(savedBookingDetails.getPackageInBooking().getPackageName());
		tc.setPacakageType(savedBookingDetails.getPackageInBooking().getPackageType());
		
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
		
		existingBooking.get().getPackageInBooking().getBookingList().remove(existingBooking.get());
		existingBooking.get().setPackageInBooking(null);
		
		
		bRepo.save(existingBooking.get());
		
		bRepo.delete(existingBooking.get());
		
		return "Ticket with Booking Id :  "+bookingId +" has been cancelled successfully..";
	}

	
	@Override
	public TicketDetails viewBookingByBookingIdForCustomer(Integer bookingId, String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Admin)) {
			throw new CredentialException("Kindly login as Customer");
		}
		
		Optional<Booking> existingBooking = bRepo.findById(bookingId);
		
		if(existingBooking.isEmpty()) {
			throw new BookingException("Invalid Booking ID");
		}
		
		Integer customerId = usersession.getUserId();
		
		if(customerId != existingBooking.get().getCustomer().getUserId()) {
			throw new BookingException("Invalid Booking ID");
		}
		
		Booking savedBookingDetails = existingBooking.get();
		
		
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
		tc.setPackageName(savedBookingDetails.getPackageInBooking().getPackageName());
		tc.setPacakageType(savedBookingDetails.getPackageInBooking().getPackageType());
		
		return tc;
		
		
	}
	
	
	@Override
	public TicketDetails viewBookingByBookingIdForAdmin(Integer bookingId, String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Booking> existingBooking = bRepo.findById(bookingId);
		
		if(existingBooking.isEmpty()) {
			throw new BookingException("Invalid Booking ID");
		}
		
		Booking savedBookingDetails = existingBooking.get();
		
		
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
		tc.setPackageName(savedBookingDetails.getPackageInBooking().getPackageName());
		tc.setPacakageType(savedBookingDetails.getPackageInBooking().getPackageType());
		
		
		return tc;
		
		
	}
	

	@Override
	public List<TicketDetails> viewBookingByCustomerIdForAdmin(Integer customerId, String key)
			throws BookingException, CredentialException, CustomerException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Customer> existingCustomer = cRepo.findById(customerId);
		
		
		if(existingCustomer.isEmpty()) {
			throw new CustomerException("Invalid customer id");
		}
		
		List<Booking> bookings = existingCustomer.get().getBookingListInCustomer();
		
		if(bookings.isEmpty()) {
			throw new BookingException("No booking found for the customer with Customer Id : " + customerId);
		}
		
		List<TicketDetails> allTicketDetails = new ArrayList<>();
		
		for(Booking  b : bookings) {
			
			TicketDetails tc = new TicketDetails();
			
			tc.setTicketId(b.getBookingId());
			tc.setBookingDateTime(b.getBookingDateTime());
			tc.setBookingType(b.getBookingType());
			tc.setCustomerEmail(b.getCustomer().getCustomerEmail());
			tc.setCustomerMobile(b.getCustomer().getCustomerMobile());
			tc.setCustomerName(b.getCustomer().getCustomerName());
			tc.setNoOfPerson(b.getNoOfPerson());
			tc.setStatus(TicketStatus.CONFIRMED);
			tc.setPayment(b.getPayment());
			tc.setPackageName(b.getPackageInBooking().getPackageName());
			tc.setPacakageType(b.getPackageInBooking().getPackageType());
			
			allTicketDetails.add(tc);
			
		}
		
		
		return allTicketDetails;
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
	
	
	@Override
	public List<Booking> viewBookingByBookingTypeForAdmin(BookingType bookingType, String key) throws BookingException, CredentialException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		
		List<Booking> allBookingDetails = bRepo.findAll();
		
		if(allBookingDetails.isEmpty()) {
			throw new BookingException("No booking found with this booking type");
		}
		
		List<Booking> allBookingDetailsByType = new ArrayList<>();
		
		for(Booking booking : allBookingDetails) {
			
			if(booking.getBookingType().equals(bookingType)) {
				allBookingDetailsByType.add(booking);
			}
			
		}
		
		if(allBookingDetailsByType.isEmpty()) {
			throw new BookingException("No booking found with this booking type");
		}	
		
		return allBookingDetailsByType;
	}

	
	
	
	
	
	
	
	
}
