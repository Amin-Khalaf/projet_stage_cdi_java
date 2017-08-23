package com.umanteam.dadakar.run.back.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

@Repository
public class WayPoint {

	@Id
	private String id;
	private int order;
	private LocalDate estimatedDate;
	private LocalTime estimatedTime;
	private Duration flexibility;
	private String meetingPoint;
	private String district;
	private String town;
	private String postcode;
	private int availableSeats;
	private double priceToNextPoint;
	
	//constructors
	public WayPoint() {
		super();
	}

	public WayPoint(int order, LocalDate estimatedDate, LocalTime estimatedTime, Duration flexibility, String meetingPoint,
			String district, String town, String postcode, int availableSeats, double priceToNextPoint) {
		super();
		this.order = order;
		this.estimatedDate = estimatedDate;
		this.estimatedTime = estimatedTime;
		this.flexibility = flexibility;
		this.meetingPoint = meetingPoint;
		this.district = district;
		this.town = town;
		this.postcode = postcode;
		this.availableSeats = availableSeats;
		this.priceToNextPoint = priceToNextPoint;
	}

	// Getters
	public String getId() {
		return id;
	}

	public int getOrder() {
		return order;
	}

	public LocalDate getEstimatedDate() {
		return estimatedDate;
	}
	
	public LocalTime getEstimatedTime() {
		return estimatedTime;
	}

	public Duration getFlexibility() {
		return flexibility;
	}

	public String getMeetingPoint() {
		return meetingPoint;
	}

	public String getDistrict() {
		return district;
	}

	public String getTown() {
		return town;
	}

	public String getPostcode() {
		return postcode;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public double getPriceToNextPoint() {
		return priceToNextPoint;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setEstimatedDate(LocalDate estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public void setEstimatedTime(LocalTime estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public void setFlexibility(Duration flexibility) {
		this.flexibility = flexibility;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setPriceToNextPoint(double priceToNextPoint) {
		this.priceToNextPoint = priceToNextPoint;
	}

	// toString
	@Override
	public String toString() {
		return "Waypoint [id=" + id + ", order=" + order + ", estimatedDate=" + estimatedDate + ", estimatedTime=" + estimatedTime + ", flexibility="
				+ flexibility + ", meetingPoint=" + meetingPoint + ", district=" + district + ", town=" + town
				+ ", postcode=" + postcode + ", availableSeats=" + availableSeats + ", priceToNextPoint=" + priceToNextPoint + "]";
	}
	
	
}
