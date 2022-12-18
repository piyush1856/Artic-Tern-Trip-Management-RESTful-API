package com.articTern.service;

import java.util.List;

import com.articTern.exceptions.RouteException;
import com.articTern.model.Route;

public interface RouteService {
	

	public Route addRoute(Route route, String key) throws RouteException;
	
	public Route updateRouteFare(Route route, String key) throws RouteException;
	
	public Route updateRouteArrival(Route route, String key) throws RouteException;
	
	public Route updateRouteDeparture(Route route, String key) throws RouteException;
	
	public Route updateRouteDateOfJourney(Route route, String key) throws RouteException;
	
	public String deleteRoute(Integer routeId, String key) throws RouteException;
	
	public Route getRouteById(Integer routeId, String key) throws RouteException;
	
	public List<Route> getRouteBySourceAndDestination(String src, String des, String key) throws RouteException;
	
	public List<Route> getAllRoute(String key) throws RouteException;

}
