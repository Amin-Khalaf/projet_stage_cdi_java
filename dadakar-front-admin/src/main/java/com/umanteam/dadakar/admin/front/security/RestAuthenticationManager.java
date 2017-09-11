package com.umanteam.dadakar.admin.front.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.umanteam.dadakar.admin.front.dto.AccountToken;
import com.umanteam.dadakar.admin.front.dto.Connect;
import com.umanteam.dadakar.admin.front.enums.Role;

@Component
public class RestAuthenticationManager implements AuthenticationProvider {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${login.path}")
	private String loginPath;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		// create connect entity
		Connect connect = new Connect(username, auth.getCredentials().toString());
		// call login webservice
		HttpEntity<Connect> request = new HttpEntity<Connect>(connect);
		try {
			ResponseEntity<AccountToken> answer = restTemplate.exchange(loginPath, HttpMethod.POST, request,
					new ParameterizedTypeReference<AccountToken>() {
					});
			// error answer
			if (answer.getStatusCode() != HttpStatus.OK || answer.getBody().getAccountDTO().getRole() == Role.USER) {
				return null;
			}
			
			// get account & token
			AccountToken accountToken = answer.getBody();
			

			// put the token in httpsession
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("ddkt", accountToken.getToken());
			
			// set user Id in httpsession
			session.setAttribute("uid", accountToken.getAccountDTO().getAccountId());
			
			// set user role in httpsession
			session.setAttribute("role", accountToken.getAccountDTO().getRole().toString());
			
			// user details
			UserDetails userdetails = User
				.withUsername(username)
				.password(accountToken.getAccountDTO().getPassword())
				.roles(accountToken.getAccountDTO().getRole().toString())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
			// return infos
			return new UsernamePasswordAuthenticationToken(accountToken.getAccountDTO().getUsername(),
					accountToken.getAccountDTO().getPassword(),
					userdetails.getAuthorities());
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
