package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
