package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="waypoints")
public class WayPoint {

	@Id
	private String id;
	private String meetingPoint;
	private String district;
	private String town;
	private String postcode;

	// constructors
	public WayPoint() {
		super();
	}

	public WayPoint(String meetingPoint, String district, String town, String postcode) {
		super();
		this.meetingPoint = meetingPoint;
		this.district = district;
		this.town = town;
		this.postcode = postcode;
	}

	// Getters
	public String getId() {
		return id;
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

	// Setters
	public void setId(String id) {
		this.id = id;
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

	// toString
	@Override
	public String toString() {
		return "Waypoint [id=" + id + ", meetingPoint=" + meetingPoint + ", district=" + district + ", town=" + town
				+ ", postcode=" + postcode + "]";
	}

}
