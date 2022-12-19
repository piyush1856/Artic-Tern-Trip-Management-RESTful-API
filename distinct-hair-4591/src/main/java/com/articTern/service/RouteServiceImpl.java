package com.articTern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.articTern.enums.UserType;
import com.articTern.exceptions.BusException;
import com.articTern.exceptions.CredentialException;
import com.articTern.exceptions.RouteException;
import com.articTern.model.Bus;
import com.articTern.model.Route;
import com.articTern.model.TripPackage;
import com.articTern.model.UserSession;
import com.articTern.repository.BusRepo;
import com.articTern.repository.CustomerRepo;
import com.articTern.repository.PackageRepo;
import com.articTern.repository.RouteRepo;
import com.articTern.repository.SessionRepo;
import com.articTern.repository.TravelAgencyRepo;

@Service
public class RouteServiceImpl implements RouteService{
	
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
	
	@Autowired
	private PackageRepo pRepo;
	

	@Override
	public Route addRoute(Route route, String key) throws RouteException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)) {
			throw new CredentialException("Only Admin can add routes, Kindly login as Admin");
		}
		
		Route r = rRepo.findByRouteId(route.getRouteId());
		
		if(r!=null) {
			throw new RouteException("Route already present");
		}
		
		return rRepo.save(route);
		
	}

	@Override
	public Route updateRouteFare(Route route, String key) throws RouteException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Route> r = rRepo.findById(route.getRouteId());
		
		if(r.isEmpty()) {
			throw new RouteException("Route not found");
		}
		
		r.get().setFare(route.getFare());
		
		return rRepo.save(r.get());
	}

	@Override
	public String deleteRoute(Integer routeId, String key) throws RouteException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Route> opt = rRepo.findById(routeId);
		
		if(opt.isPresent()) {
			
			List<Bus> bus = opt.get().getRouteBusList();
			
			for(Bus b : bus) {
				b.setBusRoute(null);
			}
			
			opt.get().getRouteBusList().clear();
			
			
			List<TripPackage> packagelist = opt.get().getPackagesInRoute();
			
			if(packagelist.size() !=0) {
				
				for(TripPackage t : packagelist){
					
					List<Route> rlist = t.getRoutesInPackage();
					
					rlist.remove(opt.get());
					
				}
				
			}
			
			opt.get().getPackagesInRoute().clear();
		   
			
			rRepo.save(opt.get());
			
			rRepo.delete(opt.get());
			
			return "Route deleted successfully";
		}
		
		else {
			throw new RouteException("Route not found with this ID.");
		}
	}

	@Override
	public Route getRouteById(Integer routeId, String key) throws RouteException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null){
			throw new CredentialException("Kindly login to continue");
		}
		
		return rRepo.findById(routeId)
				    .orElseThrow(() -> new RouteException("route not found with this ID."));
	}

	@Override
	public List<Route> getAllRoute(String key) throws RouteException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null ){
			throw new CredentialException("Kindly login to continue");
		}
		
	 	if( rRepo.findAll().size() == 0) {
	 		throw new RouteException("No Route found");
	 	}else {
	 		return rRepo.findAll();
	 	}
	}

	@Override
	public List<Route> getRouteBySourceAndDestination(String src, String des, String key) throws RouteException {

		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null){
			throw new CredentialException("Kindly login to continue");
		}
		
		List<Route> routelist=rRepo.findAll();
		
		List<Route> requiredList = new ArrayList<>();
		
		for(Route r:routelist) {
			if(r.getRouteFrom().equalsIgnoreCase(src) && r.getRouteTo().equalsIgnoreCase(des)) {
				requiredList.add(r);
			}
		}
		if(requiredList.size()==0) {
			throw new RouteException("No Route Found");
		}
		
		return requiredList;
	}

	@Override
	public Route updateRouteArrival(Route route, String key) throws RouteException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Route> r = rRepo.findById(route.getRouteId());
		
		if(r.isEmpty()) {
			throw new RouteException("Route not found");
		}
		
		r.get().setArrivalTime(route.getArrivalTime());;
		
		return rRepo.save(r.get());
	}

	@Override
	public Route updateRouteDeparture(Route route, String key) throws RouteException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Route> r = rRepo.findById(route.getRouteId());
		
		if(r.isEmpty()) {
			throw new RouteException("Route not found");
		}
		
		r.get().setDepartureTime(route.getDepartureTime());;
		
		return rRepo.save(r.get());
	}

	@Override
	public Route updateRouteDateOfJourney(Route route, String key) throws RouteException {
		UserSession usersession = sRepo.findByUuid(key);
		
		if(usersession == null || usersession.getUserType().equals(UserType.Customer)){
			throw new CredentialException("Kindly login as Admin");
		}
		
		Optional<Route> r = rRepo.findById(route.getRouteId());
		
		if(r.isEmpty()) {
			throw new RouteException("Route not found");
		}
		
		r.get().setDateOfJourney(route.getDateOfJourney());;
		
		return rRepo.save(r.get());
	}

}
