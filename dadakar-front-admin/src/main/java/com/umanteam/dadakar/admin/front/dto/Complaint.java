package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

public class Complaint implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 8811400964116324401L;
	private User user;
	private int numberOfRatings;
	private double percentOfBadRatings;
	
	/* Constructors */
	
	public Complaint() {}

	public Complaint(User user, int numberOfRatings, double percentOfBadRatings) {
		this.user = user;
		this.numberOfRatings = numberOfRatings;
		this.percentOfBadRatings = percentOfBadRatings;
	}

	/* Getters and Setters */
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}

	public double getPercentOfBadRatings() {
		return percentOfBadRatings;
	}

	public void setPercentOfBadRatings(double percentOfBadRatings) {
		this.percentOfBadRatings = percentOfBadRatings;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Complaint [user=" + user + ", numberOfRatings=" + numberOfRatings + ", percentOfBadRatings="
				+ percentOfBadRatings + "]";
	}
	
}
