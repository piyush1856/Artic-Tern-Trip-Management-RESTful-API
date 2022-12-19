package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Bus;
import com.articTern.model.Customer;
import com.articTern.model.Route;
import com.articTern.model.TravelAgency;
import com.articTern.model.UserSession;
import com.articTern.repository.BusRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.RouteRepo;
import com.articTern.repository.SessionRepo;
import com.articTern.repository.TravelAgencyRepo;

@Service
public class TravelAgencyServiceImpl implements TravelAgencyService{
	
	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private SessionRepo sRepo;
	
	@Autowired
	private TravelAgencyRepo tRepo;
	
	@Autowired
	private BusRepo bRepo;
	
	@Autowired
	private RouteRepo rRepo;

	@Override
	public TravelAgency addtravelAgency(TravelAgency travelAgency, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		TravelAgency ta = tRepo.findByTravelMobile(travelAgency.getTravelMobile());
		
		if(ta != null) {
			throw new TravelAgencyException("Travel Agency already registered with this mobile number.");
		}
		
		return tRepo.save(travelAgency);
	}

	@Override
	public TravelAgency updateTravelAgencyName(TravelAgency travelAgency, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		TravelAgency ta = tRepo.findByTravelMobile(travelAgency.getTravelMobile());
		
		if(ta == null) {
			throw new TravelAgencyException("Travel Agency not registered with this mobile number.");
		}
		
		ta.setTravelsName(travelAgency.getTravelsName());
		
		return tRepo.save(ta);
		
		
	}

	@Override
	public TravelAgency updateTravelAgentName(TravelAgency travelAgency, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		TravelAgency ta = tRepo.findByTravelMobile(travelAgency.getTravelMobile());
		
		if(ta == null) {
			throw new TravelAgencyException("Travel Agency not registered with this mobile number.");
		}
		
		ta.setAgentName(travelAgency.getAgentName());
		
		return tRepo.save(ta);
	}

	@Override
	public TravelAgency updateTravelAgencyAddress(TravelAgency travelAgency, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		TravelAgency ta = tRepo.findByTravelMobile(travelAgency.getTravelMobile());
		
		if(ta == null) {
			throw new TravelAgencyException("Travel Agency not registered with this mobile number.");
		}
		
		ta.setTravelAdresses(travelAgency.getTravelAdresses());
		
		return tRepo.save(ta);
	}

	@Override
	public TravelAgency updateTravelAgencyMobile(TravelAgency travelAgency, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		TravelAgency ta = tRepo.findById(travelAgency.getTravelId()).get();
		
		if(ta == null) {
			throw new TravelAgencyException("Travel Agency not registered with this mobile number.");
		}
		
		ta.setTravelMobile(travelAgency.getTravelMobile());
		
		return tRepo.save(ta);

	}

	@Override
	public String deleteTravelAgencyById(Integer travelId, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		

		Optional<TravelAgency> opt = tRepo.findById(travelId);
		
		if(opt.isEmpty()) {
			throw new TravelAgencyException("Invalid travel id");
		}
		
		TravelAgency travelAgency = opt.get();
		
		List<Bus> busList = travelAgency.getBusList();
		
		if(! busList.isEmpty()) {
			
			for(Bus b : busList) {
				
				Route route = b.getBusRoute();
				if(route != null) {
					route.getRouteBusList().remove(b);
					b.setBusRoute(null);
				}
				
			}
		}
		
		tRepo.delete(travelAgency);
		
		return " Travel Agency deleted successfully...";		
		

		
	}

	@Override
	public TravelAgency searchById(Integer travelId, String key) throws TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		return tRepo.findById(travelId).orElseThrow(() -> new TravelAgencyException("Travel Agency not found with this ID."));
	}

	@Override
	public List<TravelAgency> viewAllTravelAgency(String key) throws TravelAgencyException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
	 	if( tRepo.findAll().size() == 0) {
	 		throw new TravelAgencyException("No Travel Agency found");
	 	}else {
	 		return tRepo.findAll();
	 	}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
