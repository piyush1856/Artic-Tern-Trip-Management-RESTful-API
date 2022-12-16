package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Feedback;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Integer>{

	 
}
