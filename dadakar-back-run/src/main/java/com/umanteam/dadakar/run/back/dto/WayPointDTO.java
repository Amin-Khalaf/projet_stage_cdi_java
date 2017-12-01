package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

import com.umanteam.dadakar.run.back.entities.Address;

public class WayPointDTO implements Serializable {

	private static final long serialVersionUID = -6852065494792942275L;
	
	private String id;
	private String meetingPoint;
	private Address address;

	// Constructors
	public WayPointDTO() {
		super();
	}

	public WayPointDTO(String meetingPoint) {
		super();
		this.meetingPoint = meetingPoint;
	}

	public WayPointDTO(String meetingPoint, Address address) {
		super();
		this.meetingPoint = meetingPoint;
		this.address = address;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getMeetingPoint() {
		return meetingPoint;
	}

	public Address getAddress() {
		return address;
	}

	// Setters
	public void setId(String id) {
		this.id = id;
	}

	public void setMeetingPoint(String meetingPoint) {
		this.meetingPoint = meetingPoint;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// toString
	@Override
	public String toString() {
		return "WaypointDTO [id=" + id + ", meetingPoint=" + meetingPoint + ", address =" + address + "]";
	}

}
