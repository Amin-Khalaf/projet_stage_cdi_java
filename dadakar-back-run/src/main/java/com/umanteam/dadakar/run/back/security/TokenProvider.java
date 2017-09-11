package com.umanteam.dadakar.run.back.security;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;

@Component
public class TokenProvider {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${server.dadakar.back.userdetails.path}")
	private String path;
	
	private final String secretKey;
	
	public TokenProvider(AppConfig config) {
		this.secretKey = Base64.getEncoder().encodeToString(config.getSecret().getBytes());
	}
	
	public Authentication getAuthentication(String token) {
		String username = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody().getSubject();
		ResponseEntity<Detail> response = restTemplate.getForEntity(path + username, Detail.class);
		Detail detail = response.getBody();
		UserDetails userDetails = User
				.withUsername(detail.getUsername())
				.password(detail.getPassword())
				.roles(detail.getAuthorities().toString())
				.accountExpired(detail.isAccountExpired())
				.accountLocked(detail.isAccountLocked())
				.credentialsExpired(detail.isCredentialExpired())
				.disabled(detail.isDisabled())
				.build();
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
}
