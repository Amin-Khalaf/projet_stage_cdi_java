package com.umanteam.dadakar.run.back.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.run.back.enums.Luggage;

@Document(collection="runs")
public class Run {

	@Id
	private String runId;
	private String driverId;
	private Vehicle vehicle;
	private List<SubRun> subRuns;
	private Luggage luggageType;
	
	// Constructors
	public Run() {
		super();
	}

	public Run(String driverId, Vehicle vehicle, Luggage luggageType) {
		super();
		this.driverId = driverId;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
	}

	public Run(String driverId, Vehicle vehicle, List<SubRun> subRuns, Luggage luggageType) {
		super();
		this.driverId = driverId;
		this.vehicle = vehicle;
		this.subRuns = subRuns;
		this.luggageType = luggageType;
	}

	// Getters
	public String getRunId() {
		return runId;
	}

	public String getDriverId() {
		return driverId;
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

	public void setDriverId(String driverId) {
		this.driverId = driverId;
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
		return "Run [runId=" + runId + ", driverId=" + driverId + " ,vehicle=" + vehicle + ", subRuns=" + subRuns + ", luggageType=" + luggageType + "]";
	}
	
}
