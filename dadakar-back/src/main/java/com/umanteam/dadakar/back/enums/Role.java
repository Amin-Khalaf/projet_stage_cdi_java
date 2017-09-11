package com.umanteam.dadakar.back.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	SUPERUSER,
	ADMIN,
	USER;

	@Override
	public String getAuthority() {
		return this.toString();
	}
}
