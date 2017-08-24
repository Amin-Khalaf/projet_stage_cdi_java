package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SubRunDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = -2918947042391451084L;
	private String subRunId;
	private Duration flexibility;
	private WayPointDTO startPlace;
	private WayPointDTO endPlace;
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(iso=ISO.TIME)
	@JsonFormat(pattern="HH:mm")
	private LocalTime startTime;
	@DateTimeFormat(iso=ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate estimatedEndDate;
	@DateTimeFormat(iso=ISO.TIME)
	@JsonFormat(pattern="HH:mm")
	private LocalTime estimatedEndTime;
	private int availableSeats;
	private List<PassengerDTO> passengers;
	private List<WayPointDTO> startingPoints;
	private List<TollDTO> tolls;
	private double price;

	/* Constructors */
	
	public SubRunDTO() {}

	public SubRunDTO(Duration flexibility, WayPointDTO startPlace, WayPointDTO endPlace, LocalDate startDate,
			LocalTime startTime, LocalDate estimatedEndDate, LocalTime estimatedEndTime, int availableSeats,
			List<PassengerDTO> passengers, List<WayPointDTO> startingPoints, List<TollDTO> tolls, double price) {
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

	public WayPointDTO getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(WayPointDTO startPlace) {
		this.startPlace = startPlace;
	}

	public WayPointDTO getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(WayPointDTO endPlace) {
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

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public List<PassengerDTO> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDTO> passengers) {
		this.passengers = passengers;
	}

	public List<WayPointDTO> getStartingPoints() {
		return startingPoints;
	}

	public void setStartingPoints(List<WayPointDTO> startingPoints) {
		this.startingPoints = startingPoints;
	}

	public List<TollDTO> getTolls() {
		return tolls;
	}

	public void setTolls(List<TollDTO> tolls) {
		this.tolls = tolls;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "SubRunDTO [subRunId=" + subRunId + ", flexibility=" + flexibility + ", startPlace=" + startPlace
				+ ", endPlace=" + endPlace + ", startDate=" + startDate + ", startTime=" + startTime
				+ ", estimatedEndDate=" + estimatedEndDate + ", estimatedEndTime=" + estimatedEndTime
				+ ", availableSeats=" + availableSeats + ", passengers=" + passengers + ", startingPoints="
				+ startingPoints + ", tolls=" + tolls + ", price=" + price + "]";
	}
	
}
