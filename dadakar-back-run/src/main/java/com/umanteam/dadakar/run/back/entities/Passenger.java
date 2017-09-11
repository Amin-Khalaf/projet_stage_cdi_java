package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.enums.ResState;

@Document(collection="passengers")
public class Passenger {
	
	/*Variables */
	
	@Id
	private String passengerId;
	private User user;
	private Luggage luggage;
	private Double price;
	private ResState reservationState;
	
	/* Constructors */
	
	public Passenger() {}

	public Passenger(User user, Luggage luggage, Double price) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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
		return "Passenger [passengerId=" + passengerId + ", user=" + user + ", luggage=" + luggage + ", price=" + price
				+ ", reservationState=" + reservationState + "]";
	}

}
