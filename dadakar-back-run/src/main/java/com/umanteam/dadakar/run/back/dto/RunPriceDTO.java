package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

public class RunPriceDTO implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -8032935306636100709L;
	private String runPriceId;
	private int power;
	private double startingPrice;
	private double maxPrice;
	private double minPrice;
	private double rate;
	
	/* Constructors */
	
	public RunPriceDTO() {
	}

	public RunPriceDTO(int power, double startingPrice, double maxPrice, double minPrice, double rate) {
		this.power = power;
		this.startingPrice = startingPrice;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.rate = rate;
	}

	/* Getters and Setters */
	
	public String getRunPriceId() {
		return runPriceId;
	}

	public void setRunPriceId(String runPriceId) {
		this.runPriceId = runPriceId;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "RunPriceDTO [runPriceId=" + runPriceId + ", power=" + power + ", startingPrice=" + startingPrice
				+ ", maxPrice=" + maxPrice + ", minPrice=" + minPrice + ", rate=" + rate + "]";
	}

}
