package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.TravelAgency;

@Repository
public interface TravelAgencyRepo extends JpaRepository<TravelAgency, Integer>{
	
	public TravelAgency findByTravelMobile(String travelMobile);
	
	

}
