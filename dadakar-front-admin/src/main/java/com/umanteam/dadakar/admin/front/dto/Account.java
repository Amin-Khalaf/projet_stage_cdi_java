package com.umanteam.dadakar.admin.front.dto;

import java.io.Serializable;

import com.umanteam.dadakar.admin.front.enums.Role;

public class Account implements Serializable {

	/* Variables */
	
	private static final long serialVersionUID = 2061274277087891498L;
	private String accountId;
	private String username;
	private String password;
	private Role role;
	private boolean banned;
	private boolean deleted;
	
	/* Constructors */
	
	public Account() {}
	
	public Account(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/* Getters and Setters */
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

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
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/* Methods */
	
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", password=" + password + ", role="
				+ role + ", banned=" + banned + ", deleted=" + deleted + "]";
	}

}

