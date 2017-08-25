package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

public class WayPointDTO implements Serializable {

	private static final long serialVersionUID = -6852065494792942275L;
	
	private String id;
	private String meetingPoint;
	private String district;
	private String town;
	private String postcode;

	// Constructors
	public WayPointDTO() {
		super();
	}

	public WayPointDTO(String meetingPoint, String district, String town, String postcode) {
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
		return "WaypointDTO [id=" + id + ", meetingPoint=" + meetingPoint + ", district=" + district
				+ ", town=" + town + ", postcode=" + postcode + "]";
	}

}
