package com.umanteam.dadakar.run.back.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.enums.Luggage;

@Repository
public class Run {

	@Id
	private String runId;
	private User driver;
	private List<WayPoint> wayPoints;
	private List<Passenger> passengers;
	private List<Toll> tolls;
	private Luggage luggageType;
	
	// Constructors
	public Run() {
		super();
	}

	public Run(User driver, List<WayPoint> wayPoints, List<Passenger> passengers, List<Toll> tolls,
			Luggage luggageType) {
		super();
		this.driver = driver;
		this.wayPoints = wayPoints;
		this.passengers = passengers;
		this.tolls = tolls;
		this.luggageType = luggageType;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public User getDriver() {
		return driver;
	}

	public List<WayPoint> getWayPoints() {
		return wayPoints;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public List<Toll> getTolls() {
		return tolls;
	}

	public Luggage getLuggageType() {
		return luggageType;
	}

	// Setters
	public void setRunId(String runId) {
		this.runId = runId;
	}

	public void setDriver(User driver) {
		this.driver = driver;
	}

	public void setWayPoints(List<WayPoint> wayPoints) {
		this.wayPoints = wayPoints;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setTolls(List<Toll> tolls) {
		this.tolls = tolls;
	}

	public void setLuggageType(Luggage luggageType) {
		this.luggageType = luggageType;
	}

	// toString
	@Override
	public String toString() {
		return "Run [runId=" + runId + ", driver=" + driver + ", wayPoints=" + wayPoints + ", passengers=" + passengers
				+ ", tolls=" + tolls + ", luggageType=" + luggageType + "]";
	}
	
}
