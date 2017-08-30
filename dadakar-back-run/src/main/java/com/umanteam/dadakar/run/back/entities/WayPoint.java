package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="waypoints")
public class WayPoint {

	@Id
	private String id;
	private String meetingPoint;
	private Address address;

	// constructors
	public WayPoint() {
		super();
	}

	public WayPoint(String meetingPoint) {
		super();
		this.meetingPoint = meetingPoint;
	}
	
	public WayPoint(String meetingPoint, Address address) {
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
	
	public Address getAddress(){
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
		return "Waypoint [id=" + id + ", meetingPoint=" + meetingPoint + ", address=" + address + "]";
	}

}
