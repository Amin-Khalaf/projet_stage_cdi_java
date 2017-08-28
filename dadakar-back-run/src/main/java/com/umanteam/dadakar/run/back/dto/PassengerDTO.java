package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.enums.ResState;

public class PassengerDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 1311439521320518471L;
	private String passengerId;
	private String userId;
	private Luggage luggage;
	private Double price;
	private ResState reservationState;
	
	/* Constructors */
	
	public PassengerDTO() {}

	public PassengerDTO(String userId, Luggage luggage, Double price) {
		this.userId = userId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return "PassengerDTO [passengerId=" + passengerId + ", userId=" + userId + ", luggage=" + luggage + ", price="
				+ price + ", reservationState=" + reservationState + "]";
	}
	
}
