package com.umanteam.dadakar.back.full.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.enums.Luggage;

@Repository
public class Run {

	@Id
	private String runId;
	private User driver;
	private Vehicle vehicle;
	private List<SubRun> subRuns;
	private Luggage luggageType;
	
	// Constructors
	public Run() {
		super();
	}

	public Run(User driver, Vehicle vehicle, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
	}

	public Run(User driver, Vehicle vehicle, List<SubRun> subRuns, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.subRuns = subRuns;
		this.luggageType = luggageType;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public User getDriver() {
		return driver;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<SubRun> getSubRuns() {
		return subRuns;
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setSubRuns(List<SubRun> subRuns) {
		this.subRuns = subRuns;
	}

	public void setLuggageType(Luggage luggageType) {
		this.luggageType = luggageType;
	}

	// toString
	@Override
	public String toString() {
		return "Run [runId=" + runId + ", driver=" + driver + " ,vehicle=" + vehicle + ", subRuns=" + subRuns + ", luggageType=" + luggageType + "]";
	}
	
}
