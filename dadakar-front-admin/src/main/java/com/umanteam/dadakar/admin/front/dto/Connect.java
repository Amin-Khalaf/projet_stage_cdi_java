package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

public class Connect implements Serializable {
	
	/* Variable */
	
	private static final long serialVersionUID = -3068834466208555935L;
	private String username;
	private String password;
	
	/* Constructors */
	
	public Connect() {}

	public Connect(String username, String password) {
		this.username = username;
		this.password = DigestUtils.sha1Hex(password);
	}

	/* Getters and Setters */
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.sha1Hex(password);
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Connect [username=" + username + ", password=" + password + "]";
	}
	
}
