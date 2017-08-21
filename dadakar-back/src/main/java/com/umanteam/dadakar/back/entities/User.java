package com.umanteam.dadakar.back.entities;

public class User {
	
	private String firstName;

	public User() {}
	
	public User(String firstName) {
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
		return "User [firstName=" + firstName + "]";
	}


}
