package com.articTern.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.enums.PackageType;
import com.articTern.model.TripPackage;

@Repository
public interface PackageRepo extends JpaRepository<TripPackage, Integer> {
	
	public List<TripPackage>  findByPackageType(PackageType packageType);
	
   public List<TripPackage> findBypackageCostBetween(Double minPrice, Double maxPrice);
	
}
