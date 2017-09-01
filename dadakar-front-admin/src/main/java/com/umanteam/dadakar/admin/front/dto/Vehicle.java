package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

public class Vehicle implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -9031934407414091733L;
	private String vehicleId;
	private String name;
	private String brand;
	private String model;
	private String color;
	private String photo;
	private String carRegistration;
	private String registrationNumber;
	private int power;
	
	/* Constructors */
	
	public Vehicle() {}

	public Vehicle(String name, String brand, String model, String color, String photo, String carRegistration,
			String registrationNumber, int power) {
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.photo = photo;
		this.carRegistration = carRegistration;
		this.registrationNumber = registrationNumber;
		this.power = power;
	}

	public Vehicle(String vehicleId, String name, String brand, String model, String color, String photo,
			String carRegistration, String registrationNumber, int power) {
		this.vehicleId = vehicleId;
		this.name = name;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.photo = photo;
		this.carRegistration = carRegistration;
		this.registrationNumber = registrationNumber;
		this.power = power;
	}

	/* Getters and Setters */
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCarRegistration() {
		return carRegistration;
	}

	public void setCarRegistration(String carRegistration) {
		this.carRegistration = carRegistration;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", name=" + name + ", brand=" + brand + ", model=" + model
				+ ", color=" + color + ", photo=" + photo + ", carRegistration=" + carRegistration
				+ ", registrationNumber=" + registrationNumber + ", power=" + power + "]";
	}

}
