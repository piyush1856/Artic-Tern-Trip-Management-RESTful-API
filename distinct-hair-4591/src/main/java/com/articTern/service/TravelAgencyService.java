package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.CustomerException;
import com.articTern.exceptions.TravelAgencyException;
import com.articTern.model.Customer;
import com.articTern.model.TravelAgency;

public interface TravelAgencyService {
	
	public TravelAgency addtravelAgency(TravelAgency travelAgency, String key) throws TravelAgencyException;
	
	public TravelAgency updateTravelAgencyName(TravelAgency travelAgency, String key) throws TravelAgencyException;
	
	public TravelAgency updateTravelAgentName(TravelAgency travelAgency, String key) throws TravelAgencyException;
	
	public TravelAgency updateTravelAgencyAddress(TravelAgency travelAgency, String key) throws TravelAgencyException;
	
	public TravelAgency updateTravelAgencyMobile(TravelAgency travelAgency, String key) throws TravelAgencyException;
	
	public String deleteTravelAgencyById(Integer travelId, String key) throws TravelAgencyException;
	
	public TravelAgency searchById(Integer travelId, String key) throws TravelAgencyException;
	
	public List<TravelAgency> viewAllTravelAgency(String key) throws TravelAgencyException;

}
