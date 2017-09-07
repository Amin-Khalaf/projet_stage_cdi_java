package com.umanteam.dadakar.img.back.security;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
		UserDetails userDetails = restTemplate.getForEntity(path + username, UserDetails.class).getBody();
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
}
