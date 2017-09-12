package com.umanteam.dadakar.login.back.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final TokenProvider tokenProvider;

	public SecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		.csrf()
			.disable()
		.cors()
			.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
		.httpBasic()
			.and()
		.authorizeRequests()
			.antMatchers("/login", "/signup", "/details/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.apply(new JWTConfigurer(tokenProvider));
		// @formatter:on
	}

}