package com.umanteam.dadakar.back.full.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicles")
public class Vehicle {

	@Id
	private String vehicleId;
	private String name;
	private String brand;
	private String model;
	private String color;
	private String photo;
	private String carRegistration;
	private String registrationNumber;
	private int power;

	// Constructors
	public Vehicle() {
		super();
	}

	public Vehicle(String name, String brand, String model, String color, String photo, String carRegistration,
			String registrationNumber, int power) {
		super();
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.photo = photo;
		this.carRegistration = carRegistration;
		this.registrationNumber = registrationNumber;
		this.power = power;
	}

	// Getters
	public String getVehicleId() {
		return vehicleId;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public String getPhoto() {
		return photo;
	}

	public String getCarRegistration() {
		return carRegistration;
	}

	public int getPower() {
		return power;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	// Setters
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setCarRegistration(String carRegistration) {
		this.carRegistration = carRegistration;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setPower(int power) {
		this.power = power;
	}

	// To String
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", name=" + name + ", brand=" + brand + ", model=" + model
				+ ", color=" + color + ", photo=" + photo + ", carRegistration=" + carRegistration + ", registrationNumber=" + registrationNumber + ", power=" + power
				+ "]";
	}

}
