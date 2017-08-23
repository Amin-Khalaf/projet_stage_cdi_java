package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.enums.Luggage;

public class PassengerDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 4576498159074526737L;
	private String passengerId;
	private UserDTO user;
	private WayPointDTO startPlace;
	private WayPointDTO endPlace;
	private Luggage luggage;
	private double price;
	
	/* Constructors */
	
	public PassengerDTO() {}

	public PassengerDTO(UserDTO user, WayPointDTO startPlace, WayPointDTO endPlace, Luggage luggage, double price) {
		this.user = user;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.luggage = luggage;
		this.price = price;
	}

	/* Getters and Setters */
	
	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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

	public Luggage getLuggage() {
		return luggage;
	}

	public void setLuggage(Luggage luggage) {
		this.luggage = luggage;
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
		return "PassengerDTO [passengerId=" + passengerId + ", user=" + user + ", startPlace=" + startPlace
				+ ", endPlace=" + endPlace + ", luggage=" + luggage + ", price=" + price + "]";
	}
	
}
