package com.umanteam.dadakar.run.back.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -2054239550063077088L;
	private String addressId;
	private String district;
	private String town;
	
	/* Constructors */
	
	public AddressDTO() {}

	public AddressDTO(String district, String town) {
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
		return "AddressDTO [addressId=" + addressId + ", district=" + district + ", town=" + town + "]";
	}

}
