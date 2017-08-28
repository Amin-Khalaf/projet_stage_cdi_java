package com.umanteam.dadakar.back.full.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Run;
import com.umanteam.dadakar.back.full.entities.SubRun;

public interface RunRepository extends MongoRepository<Run, String> {

	public List<Run> findByDriver(User driver);
	// find by driver not cancelled runs
	
	public List<Run> findBySubRunsExists(SubRun subRun);
	
//	public List<Run> findBySubRunPassengerUserExists(User passenger);
	// find by passenger user and run not cancelled and passenger not cancelled
	
	// find by driver or passenger
	// find by driver or passenger and run not cancelled and passenger not cancelled 
	
//	method to find runs with subrun that match the request start point and date and the end point and run not cancelled and subrun available seat gt 0
	
}
