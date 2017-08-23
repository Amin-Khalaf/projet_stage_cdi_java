package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.WayPoint;

public interface RunRepository extends MongoRepository<Run, String> {

	public Run findByDriver(User driver);
	public List<Run> findByWayPointsExists(WayPoint waypoint1);
	public List<Run> findByWayPointsExists(WayPoint waypoint1, WayPoint waypoint2);
	public List<Run> findByPassengersExists(Passenger passenger);
	public List<Run> findByTollsExists(Toll toll);
}
