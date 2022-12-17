package com.articTern.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.articTern.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{
//	
//	@Query("select b from Booking b where userId = ?1")
//	public List<Booking> findAllBookingsOfCustomer(Integer cId);
	
}
