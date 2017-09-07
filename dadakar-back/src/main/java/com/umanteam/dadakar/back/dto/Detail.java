package com.umanteam.dadakar.back.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class Detail implements Serializable {
	
	/* Variables */
	
	private static final long serialVersionUID = -1723909309710690433L;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialExpired;
	private boolean disabled;
	
	/* Constructors */
	
	public Detail() {}

	public Detail(String username, String password, Collection<? extends GrantedAuthority> authorities,
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

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
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
