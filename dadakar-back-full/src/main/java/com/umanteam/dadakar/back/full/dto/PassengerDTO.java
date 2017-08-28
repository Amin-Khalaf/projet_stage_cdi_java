package com.umanteam.dadakar.back.full.dto;

import java.io.Serializable;

import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.enums.Luggage;
import com.umanteam.dadakar.back.full.enums.ResState;

public class PassengerDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 992673263048314047L;
	private String passengerId;
	private UserDTO user;
	private Luggage luggage;
	private Double price;
	private ResState reservationState;
	
	/* Constructors */
	
	public PassengerDTO() {}

	public PassengerDTO(UserDTO user, Luggage luggage, Double price) {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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
