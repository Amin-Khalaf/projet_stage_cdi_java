package com.umanteam.dadakar.run.back.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.enums.ResState;

public interface RunRepository extends MongoRepository<Run, String> {

	// find all run by driver
	public List<Run> findByDriverId(String userid);
	// find run not cancelled by driver
	public List<Run> findByDriverIdAndCancelled(String driverId, boolean canceled);
	// find all run by subrun
	public List<Run> findBySubRunsExists(SubRun subRun);
	// find all run by passenger userId
	public List<Run> findBySubRunsPassengersUserId(String passenger);
	// find run not cancelled by passenger userId
	public List<Run> findBySubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(String passengerId, boolean canceled, List<ResState> resStates);
	// find all run by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserId(String user, String user2);
	// find run not cancelled by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotIn(String driverId, String passengerId, boolean canceled, List<ResState> resStates);
	// find current run by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(String driverId, String passengerId, LocalDate endDate, LocalTime endTime);
	// find current run not cancelled by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(String driverId, String passengerId, boolean canceled, List<ResState> resStates, LocalDate endDate, LocalTime endTime);
	// find passed run by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateLessThan(String driverId, String passengerId, LocalDate endDate);
	// find passed run not cancelled by user (as driver or as passenger)
	public List<Run> findByDriverIdOrSubRunsPassengersUserIdAndCancelledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateLessThan(String driverId, String passengerId, boolean canceled, List<ResState> resStates, LocalDate endDate);
	//	method to find runs with subrun that match the request start point and date and the end point and run not cancelled and subrun available seat gt 0
	public List<Run> findBySubRunsStartingPointsAddressDistrictAndSubRunsStartingPointsAddressTownAndSubRunsStartDateAndSubRunsEndPlaceAddressDistrictAndSubRunsEndPlaceAddressTownAndSubRunsAvailableSeatsGreaterThanAndCancelled(
			String districtFrom, String townFrom, LocalDate dateFrom, String districtTo, String townTo, int availableSeats, boolean cancelled);
	
}
