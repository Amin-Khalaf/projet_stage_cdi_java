package com.umanteam.dadakar.run.back.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="subRuns")
public class SubRun {

	/* Variables */
	
	@Id
	private String subRunId;
	private Duration flexibility;
	private WayPoint startPlace;
	private WayPoint endPlace;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate estimatedEndDate;
	private LocalTime estimatedEndTime;
	private Integer availableSeats;
	private List<Passenger> passengers;
	private List<WayPoint> startingPoints;
	private List<Toll> tolls;
	private Double price;
	
	/* Constructors */
	
	public SubRun() {}


	public SubRun(Duration flexibility, WayPoint startPlace, WayPoint endPlace, LocalDate startDate,
			LocalTime startTime, LocalDate estimatedEndDate, LocalTime estimatedEndTime, Integer availableSeats,
			List<Passenger> passengers, List<WayPoint> startingPoints, List<Toll> tolls, Double price) {
		this.flexibility = flexibility;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.startDate = startDate;
		this.startTime = startTime;
		this.estimatedEndDate = estimatedEndDate;
		this.estimatedEndTime = estimatedEndTime;
		this.availableSeats = availableSeats;
		this.passengers = passengers;
		this.startingPoints = startingPoints;
		this.tolls = tolls;
		this.price = price;
	}
	/* Getters and Setters */
	
	public String getSubRunId() {
		return subRunId;
	}

	public void setSubRunId(String subRunId) {
		this.subRunId = subRunId;
	}

	public Duration getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(Duration flexibility) {
		this.flexibility = flexibility;
	}

	public WayPoint getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(WayPoint startPlace) {
		this.startPlace = startPlace;
	}

	public WayPoint getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(WayPoint endPlace) {
		this.endPlace = endPlace;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEstimatedEndDate() {
		return estimatedEndDate;
	}

	public void setEstimatedEndDate(LocalDate estimatedEndDate) {
		this.estimatedEndDate = estimatedEndDate;
	}

	public LocalTime getEstimatedEndTime() {
		return estimatedEndTime;
	}

	public void setEstimatedEndTime(LocalTime estimatedEndTime) {
		this.estimatedEndTime = estimatedEndTime;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<WayPoint> getStartingPoints() {
		return startingPoints;
	}

	public void setStartingPoints(List<WayPoint> startingPoints) {
		this.startingPoints = startingPoints;
	}

	public List<Toll> getTolls() {
		return tolls;
	}

	public void setTolls(List<Toll> tolls) {
		this.tolls = tolls;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "SubRun [subRunId=" + subRunId + ", flexibility=" + flexibility + ", startPlace=" + startPlace
				+ ", endPlace=" + endPlace + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", estimatedEndDate=" + estimatedEndDate + ", estimatedEndTime=" + estimatedEndTime
				+ ", availableSeats=" + availableSeats + ", passengers=" + passengers + ", startingPoints="
				+ startingPoints + ", tolls=" + tolls + ", price=" + price + "]";
	}	
	
}
