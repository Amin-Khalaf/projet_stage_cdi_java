package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.util.List;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.run.back.enums.Luggage;

public class RunDTO implements Serializable {

	private static final long serialVersionUID = 7867532309907390114L;
	private String runId;
	private UserDTO driver;
	private VehicleDTO vehicle;
	private List<WayPointDTO> wayPoints;
	private List<PassengerDTO> passengers;
	private List<TollDTO> tolls;
	private Luggage luggageType;
	
	// Constructors
	public RunDTO() {
		super();
	}

	public RunDTO(UserDTO driver, VehicleDTO vehicle, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
	}

	public RunDTO(UserDTO driver, VehicleDTO vehicle, List<WayPointDTO> wayPoints, List<PassengerDTO> passengers, List<TollDTO> tolls,
			Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.wayPoints = wayPoints;
		this.passengers = passengers;
		this.tolls = tolls;
		this.luggageType = luggageType;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public UserDTO getDriver() {
		return driver;
	}

	public VehicleDTO getVehicle() {
		return vehicle;
	}

	public List<WayPointDTO> getWayPoints() {
		return wayPoints;
	}

	public List<PassengerDTO> getPassengers() {
		return passengers;
	}

	public List<TollDTO> getTolls() {
		return tolls;
	}

	public Luggage getLuggageType() {
		return luggageType;
	}

	// Setters
	public void setRunId(String runId) {
		this.runId = runId;
	}

	public void setDriver(UserDTO driver) {
		this.driver = driver;
	}

	public void setVehicle(VehicleDTO vehicle) {
		this.vehicle = vehicle;
	}

	public void setWayPoints(List<WayPointDTO> wayPoints) {
		this.wayPoints = wayPoints;
	}

	public void setPassengers(List<PassengerDTO> passengers) {
		this.passengers = passengers;
	}

	public void setTolls(List<TollDTO> tolls) {
		this.tolls = tolls;
	}

	public void setLuggageType(Luggage luggageType) {
		this.luggageType = luggageType;
	}

	// toString
	@Override
	public String toString() {
		return "RunDTO [runId=" + runId + ", driver=" + driver + ", vehicle=" + vehicle + ", wayPoints=" + wayPoints + ", passengers="
				+ passengers + ", tolls=" + tolls + ", luggageType=" + luggageType + "]";
	}
	
	
}
