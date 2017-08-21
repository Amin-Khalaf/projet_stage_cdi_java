package com.umanteam.dadakar.back.dto;

public class UserDTO {
	
	private String firstName;

	public UserDTO() {}
	
	public UserDTO(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + "]";
	}

}
