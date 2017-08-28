package com.umanteam.dadakar.back.full.dto;

import java.io.Serializable;

public class TollDTO implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 1114260076943532254L;
	private String tollId;
	private String name;
	private double price;
	
	public TollDTO() {
	}

	public TollDTO(String name, double price) {
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
		return "TollDTO [tollId=" + tollId + ", name=" + name + ", price=" + price + "]";
	}
	
}
