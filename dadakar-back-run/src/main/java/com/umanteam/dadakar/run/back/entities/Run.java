package com.umanteam.dadakar.run.back.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.umanteam.dadakar.run.back.enums.Luggage;

@Document(collection="runs")
public class Run {

	@Id
	private String runId;
	private User driver;
	private Vehicle vehicle;
	private List<SubRun> subRuns;
	private Luggage luggageType;
	private boolean cancelled;
	
	// Constructors
	public Run() {
		super();
	}

	public Run(User driver, Vehicle vehicle, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.luggageType = luggageType;
		this.cancelled = false;
	}

	public Run(User driver, Vehicle vehicle, List<SubRun> subRuns, Luggage luggageType) {
		super();
		this.driver = driver;
		this.vehicle = vehicle;
		this.subRuns = subRuns;
		this.luggageType = luggageType;
		this.cancelled = false;
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

	public boolean isCancelled() {
		return cancelled;
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

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	// toString
	@Override
	public String toString() {
		return "Run [runId=" + runId + ", driver=" + driver + " ,vehicle=" + vehicle + ", subRuns=" + subRuns + ", luggageType=" + luggageType + ", cancelled=" + cancelled + "]";
	}
	
}
