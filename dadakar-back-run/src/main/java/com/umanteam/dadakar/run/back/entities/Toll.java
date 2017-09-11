package com.umanteam.dadakar.run.back.entities;

public class Toll {

	/* Variables */
	
	private String tollId;
	private String name;
	private double price;
	
	public Toll() {
	}

	public Toll(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getTollId() {
		return tollId;
	}

	public void setTollId(String tollId) {
		this.tollId = tollId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Toll [tollId=" + tollId + ", name=" + name + ", price=" + price + "]";
	}
	
}
