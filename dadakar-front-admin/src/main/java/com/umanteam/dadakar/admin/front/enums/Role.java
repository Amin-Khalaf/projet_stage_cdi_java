package com.umanteam.dadakar.admin.front.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	SUPERUSER,
	ADMIN,
	USER;

	@Override
	public String getAuthority() {
		return this.name();
	}
}
