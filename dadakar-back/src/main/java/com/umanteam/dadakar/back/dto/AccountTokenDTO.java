package com.umanteam.dadakar.back.dto;

import java.io.Serializable;

public class AccountTokenDTO implements Serializable {
	
	/* Variable */
	
	private static final long serialVersionUID = 1918448329395411457L;
	private AccountDTO accountDTO;
	private String token;
	
	/* Constructors */
	
	public AccountTokenDTO() {
	}

	public AccountTokenDTO(AccountDTO accountDTO, String token) {
		this.accountDTO = accountDTO;
		this.token = token;
	}

	/* Getters and Setters */
	
	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
