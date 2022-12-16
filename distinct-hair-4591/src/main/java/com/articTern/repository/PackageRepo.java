package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.TripPackage;

@Repository
public interface PackageRepo extends JpaRepository<TripPackage, Integer> {
	
	
	
}
