package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer>{
	
	public Route findByRouteId(Integer routeId); 
	

}
