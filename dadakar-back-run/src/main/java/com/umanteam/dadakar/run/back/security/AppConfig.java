package com.umanteam.dadakar.run.back.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="app")
@Component
public class AppConfig {
	
	/* Variables */
	
	private String secret;
	private long tokenValidityInSeconds;
	
	/* Getters and Setters */
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public long getTokenValidityInSeconds() {
		return tokenValidityInSeconds;
	}
	
	public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
		this.tokenValidityInSeconds = tokenValidityInSeconds;
	}

}
