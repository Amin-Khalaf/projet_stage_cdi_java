package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.enums.ResState;

public class PassengerDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 5072891816502548191L;
	private String passengerId;
	private UserDTO user;
	private Luggage luggage;
	private double price;
	private ResState reservationState;
	
	/* Constructors */
	
	public PassengerDTO() {}

	public PassengerDTO(UserDTO user, Luggage luggage, double price) {
		this.user = user;
		this.luggage = luggage;
		this.price = price;
		this.reservationState = ResState.PENDING;
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

	public ResState getReservationState() {
		return reservationState;
	}

	public void setReservationState(ResState reservationState) {
		this.reservationState = reservationState;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "PassengerDTO [passengerId=" + passengerId + ", user=" + user + ", luggage=" + luggage + ", price="
				+ price + ", reservationState=" + reservationState + "]";
	}
	
}
