package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

public class WaypointDTO implements Serializable {

	private static final long serialVersionUID = -6852065494792942275L;
	
	private String id;
	private int order;
	private LocalDateTime estimatedDateTime;
	private Duration flexibility;
	private String meetingPoint;
	private String district;
	private String town;
	private String postcode;
	private int availableSeats;

	// Constructors
	public WaypointDTO() {
		super();
	}

	public WaypointDTO(String id, int order, LocalDateTime estimatedDateTime, Duration flexibility,
			String meetingPoint, String district, String town, String postcode, int availableSeats) {
		super();
		this.id = id;
		this.order = order;
		this.estimatedDateTime = estimatedDateTime;
		this.flexibility = flexibility;
		this.meetingPoint = meetingPoint;
		this.district = district;
		this.town = town;
		this.postcode = postcode;
		this.availableSeats = availableSeats;
	}

	// Getters
	public String getId() {
		return id;
	}

	public int getOrder() {
		return order;
	}

	public LocalDateTime getEstimatedDateTime() {
		return estimatedDateTime;
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

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setEstimatedDateTime(LocalDateTime estimatedDateTime) {
		this.estimatedDateTime = estimatedDateTime;
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

	// toString
	@Override
	public String toString() {
		return "WaypointDTO [id=" + id + ", order=" + order + ", estimatedDateTime=" + estimatedDateTime
				+ ", flexibility=" + flexibility + ", meetingPoint=" + meetingPoint + ", district=" + district
				+ ", town=" + town + ", postcode=" + postcode + ", availableSeats=" + availableSeats + "]";
	}

	
}
