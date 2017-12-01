package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

public class Complaint implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 8811400964116324401L;
	private AccountUser accountUser;
	private int numberOfRatings;
	private double percentOfBadRatings;
	
	/* Constructors */
	
	public Complaint() {}

	public Complaint(AccountUser accountUser, int numberOfRatings, double percentOfBadRatings) {
		this.accountUser = accountUser;
		this.numberOfRatings = numberOfRatings;
		this.percentOfBadRatings = percentOfBadRatings;
	}

	/* Getters and Setters */
	
	public AccountUser getAccountUser() {
		return accountUser;
	}

	public void setAccountUser(AccountUser accountUser) {
		this.accountUser = accountUser;
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
		return "Complaint [accountUser=" + accountUser + ", numberOfRatings=" + numberOfRatings + ", percentOfBadRatings="
				+ percentOfBadRatings + "]";
	}
	
}
