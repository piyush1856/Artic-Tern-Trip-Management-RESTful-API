package com.articTern.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.BusException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.RouteException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Bus;
import com.articTern.model.Route;
import com.articTern.model.TravelAgency;
import com.articTern.model.UserSession;
import com.articTern.repository.BusRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.RouteRepo;
import com.articTern.repository.SessionRepo;
import com.articTern.repository.TravelAgencyRepo;

@Service
public class BusServiceImpl implements BusService{

	

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
	public Bus addBus(Bus bus, String key,Integer travelId) throws BusException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Kindly login as Admin");
		}
		
		Bus b = bRepo.findByBusNumber(bus.getBusNumber());
		
		if(b!=null) {
			throw new BusException("Bus already registered with this number : "+ bus.getBusNumber());
		}
		
		Optional<TravelAgency> opt = tRepo.findById(travelId);
		
		if(opt.isEmpty()) {
			throw new BusException("No travel Agency found with this Id");
		}
		
		
	    bRepo.save(bus);
	    bus.setTravelAgency(opt.get());
	    
	    return bRepo.save(bus);
	}

	@Override
	public Bus updateBusType(Bus bus, String key) throws BusException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Bus b = bRepo.findByBusNumber(bus.getBusNumber());
		
		if(b == null) {
			throw new BusException("Bus is not registered with this bus number.");
		}
		
		b.setBusType(bus.getBusType());
		
		return bRepo.save(b);
	}

	@Override
	public String deleteBus(Integer busId, String key) throws BusException {

		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Bus>  opt = bRepo.findById(busId);
		
		if(opt.isPresent()) {
			
			opt.get().setTravelAgency(null);
			
			opt.get().setBusRoute(null);
			
			bRepo.save(opt.get());
			
			bRepo.delete(opt.get());
			
			return " Bus deleted successfully";
		}
		else {
			throw new BusException("Bus not found with this ID.");
		}
	}

	@Override
	public Bus getBusById(Integer busId, String key) throws BusException {
 
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null){
			throw new CredentialException("Kindly login to continue");
		}
		
		return bRepo.findById(busId)
				    .orElseThrow(() -> new BusException("Bus not found with this ID."));
	}

	@Override
	public List<Bus> getAllBus(String key) throws BusException {
	
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null ){
			throw new CredentialException("Kindly login to continue");
		}
		
	 	if( bRepo.findAll().size() == 0) {
	 		throw new BusException("No Bus found");
	 	}else {
	 		return bRepo.findAll();
	 	}
	}

	@Override
	public Bus assignBusToTravelAgency(Integer busId, Integer travelsId, String key)throws BusException, TravelAgencyException {
		
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Bus> optBus = bRepo.findById(busId);
		
		if(optBus.isEmpty()) {
			throw new BusException("No Bus found with Id : " + busId);
		}
		
		Optional<TravelAgency> ta = tRepo.findById(travelsId);
		
		if(ta.isEmpty()) {
			throw new BusException("No Travel Agency found with Id : " + travelsId);
		}
		
		optBus.get().setTravelAgency(ta.get());
		ta.get().getBusList().add(optBus.get());
		
		return bRepo.save(optBus.get());
		
		
	}

	@Override
	public Bus assignBusToRoute(Integer busId, Integer routeId, String key) throws BusException, RouteException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Bus> optBus = bRepo.findById(busId);
		
		if(optBus.isEmpty()) {
			throw new BusException("No Bus found with Id : " + busId);
		}
		
		Optional<Route> ta = rRepo.findById(routeId);
		
		if(ta.isEmpty()) {
			throw new BusException("No Route found with Id : " + routeId);
		}
		
		optBus.get().setBusRoute(ta.get());
		ta.get().getRouteBusList().add(optBus.get());
		
		return bRepo.save(optBus.get());
	}
}
























