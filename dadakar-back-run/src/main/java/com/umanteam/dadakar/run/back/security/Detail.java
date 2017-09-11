package com.umanteam.dadakar.run.back.security;

import java.io.Serializable;

import com.umanteam.dadakar.run.back.enums.Role;

public class Detail implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = 6641545389173473447L;
	private String username;
	private String password;
	private Role authorities;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialExpired;
	private boolean disabled;
	
	/* Constructors */
	
	public Detail() {}

	public Detail(String username, String password, Role authorities,
			boolean accountExpired, boolean accountLocked, boolean credentialExpired, boolean disabled) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialExpired = credentialExpired;
		this.disabled = disabled;
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
		this.password = password;
	}

	public Role getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Role authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialExpired() {
		return credentialExpired;
	}

	public void setCredentialExpired(boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}

	public boolean isDisabled() {
		return disabled;
	}
	
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
