package com.umanteam.dadakar.run.back.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.enums.ResState;

public interface RunRepository extends MongoRepository<Run, String> {

	// find all run by driver
	public List<Run> findByDriver(User driver);
	
	// find all run by subrun
	public List<Run> findBySubRunsExists(SubRun subRun);
	
	// find all run by passenger user
	public List<Run> findBySubRunsPassengersUserExists(User passenger);
	
	// find by user (as driver or as passenger
	public List<Run> findByDriverOrSubRunsPassengersUserExists(User user);
	
//	method to find runs with subrun that match the request start point and date and the end point and run not cancelled and subrun available seat gt 0
	public List<Run> findBySubRunsStartingPointsDistrictAndSubRunsStartingPointsTownAndSubRunsStartDateAndSubRunsEndPlaceDistrictAndSubRunsEndPlaceTownAndSubRunsAvailableSeatsGreaterThan(
			String districtFrom, String townFrom, LocalDate dateFrom, String districtTo, String townTo, int availableSeats);
	
}
