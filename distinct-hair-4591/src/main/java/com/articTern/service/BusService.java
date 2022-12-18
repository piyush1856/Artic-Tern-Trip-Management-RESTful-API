package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.BusException;
import com.articTern.exceptions.RouteException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Bus;
import com.articTern.model.TravelAgency;


public interface BusService {
	
	public Bus addBus(Bus bus, String key, Integer travelId) throws BusException;
	
	public Bus updateBusType(Bus bus, String key) throws BusException;
	
	public String deleteBus(Integer busId, String key) throws BusException;
	
	public Bus getBusById(Integer busId, String key) throws BusException;
	
	public List<Bus> getAllBus(String key) throws BusException;
	
	public Bus assignBusToTravelAgency(Integer busId, Integer travelsId, String key) throws BusException, TravelAgencyException;
	
	public Bus assignBusToRoute(Integer busId, Integer routeId, String key) throws BusException, RouteException;

}
