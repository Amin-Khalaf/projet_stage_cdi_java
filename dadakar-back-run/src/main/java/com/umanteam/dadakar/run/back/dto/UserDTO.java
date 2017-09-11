package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -3941789539836780527L;
	private String userId;
	private String accountId;
	private String firstName;
	private String lastName;
	private String mail;
	private String idCard;
	private String photo;
	private String drivingLicence;
	private List<VehicleDTO> vehicles;
	private List<RatingDTO> ratings;

	/* Constructors */
	
	public UserDTO() {}

	public UserDTO(String accountId, String firstName, String lastName, String mail, String idCard, String photo, String drivingLicence) {
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.idCard = idCard;
		this.photo = photo;
		this.drivingLicence = drivingLicence;
	}

	/* Getters and Setters */
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccount(String accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}

	public List<VehicleDTO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleDTO> vehicles) {
		this.vehicles = vehicles;
	}

	public List<RatingDTO> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingDTO> ratings) {
		this.ratings = ratings;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", accountId=" + accountId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mail=" + mail + ", idCard=" + idCard + ", photo=" + photo + ", drivingLicence="
				+ drivingLicence + ", vehicles=" + vehicles + ", ratings=" + ratings + "]";
	}
	
}
