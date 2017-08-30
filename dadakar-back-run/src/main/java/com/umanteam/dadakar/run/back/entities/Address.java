package com.umanteam.dadakar.run.back.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="addresses")
public class Address {
	
	/* Variables */
	
	@Id
	private String addressId;
	private String district;
	private String town;
	
	/* Constructors */
	
	public Address() {}

	public Address(String district, String town) {
		this.district = district;
		this.town = town;
	}
	
	/* Getters and Setters */
	
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", district=" + district + ", town=" + town + "]";
	}
	
}
