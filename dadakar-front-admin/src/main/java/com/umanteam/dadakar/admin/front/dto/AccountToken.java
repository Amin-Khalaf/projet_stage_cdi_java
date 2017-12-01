package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;


public class AccountToken implements Serializable {
	
	/* Variable */
	
	private static final long serialVersionUID = 1918448329395411457L;
	private Account accountDTO;
	private String token;
	
	/* Constructors */
	
	public AccountToken() {
	}

	public AccountToken(Account accountDTO, String token) {
		this.accountDTO = accountDTO;
		this.token = token;
	}

	/* Getters and Setters */
	
	public Account getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(Account accountDTO) {
		this.accountDTO = accountDTO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
