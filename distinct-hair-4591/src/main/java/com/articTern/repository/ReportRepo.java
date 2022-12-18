package com.articTern.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.articTern.model.Report;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer>{

}
