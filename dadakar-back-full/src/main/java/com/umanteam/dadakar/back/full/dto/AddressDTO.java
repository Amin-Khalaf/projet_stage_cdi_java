package com.umanteam.dadakar.back.full.dto;

import java.io.Serializable;

public class AddressDTO implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -50966844903401783L;
	private String addressId;
	private String district;
	private String town;
	private String postCode;
	
	/* Constructors */
	
	public AddressDTO() {}

	public AddressDTO(String district, String town, String postCode) {
		this.district = district;
		this.town = town;
		this.postCode = postCode;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	/* Methods */
	
	@Override
	public String toString() {
		return "AddressDTO [addressId=" + addressId + ", district=" + district + ", town=" + town + ", postCode="
				+ postCode + "]";
	}

}
