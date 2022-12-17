package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Bus;

@Repository
public interface BusRepo extends JpaRepository<Bus, Integer> {
	
	public Bus findByBusNumber(String busNumber);

}
