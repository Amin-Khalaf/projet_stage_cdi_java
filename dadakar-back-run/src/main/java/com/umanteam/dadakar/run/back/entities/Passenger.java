package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.Luggage;

@Document(collection="passengers")
public class Passenger {
	
	/*Variables */
	
	@Id
	private String passengerId;
	private User user;
	private WayPoint startPlace;
	private WayPoint endPlace;
	private Luggage luggage;
	private double price;
	
	/* Constructors */
	
	public Passenger() {}

	public Passenger(User user, WayPoint startPlace,
			WayPoint endPlace, Luggage luggage, double price) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WayPoint getStartPlace() {
		return startPlace;
	}

	public void setStartPlace(WayPoint startPlace) {
		this.startPlace = startPlace;
	}

	public WayPoint getEndPlace() {
		return endPlace;
	}

	public void setEndPlace(WayPoint endPlace) {
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
		return "Passenger [passengerId=" + passengerId + ", user=" + user + ", startPlace=" + startPlace + ", endPlace="
				+ endPlace + ", luggage=" + luggage + ", price=" + price + "]";
	}

}
