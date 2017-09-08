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
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.umanteam.dadakar.admin.front.dto.AccountToken;
import com.umanteam.dadakar.admin.front.dto.Connect;

@Component
public class RestAuthenticationManager implements AuthenticationProvider {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${login.path}")
	private String loginPath;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		// create connect entity
		Connect connect = new Connect(auth.getName(), auth.getCredentials().toString());
		// call login webservice
		HttpEntity<Connect> request = new HttpEntity<Connect>(connect);
		try {
			ResponseEntity<AccountToken> answer = restTemplate.exchange(loginPath, HttpMethod.POST, request,
					new ParameterizedTypeReference<AccountToken>() {
					});
			// error answer
			if (answer.getStatusCode() != HttpStatus.OK) {
				return null;
			}
			// get account & token
			AccountToken accountToken = answer.getBody();

			// put the token in httpsession
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("ddkt", accountToken.getToken());

			// return infos
			return new UsernamePasswordAuthenticationToken(accountToken.getAccountDTO().getUsername(),
					accountToken.getAccountDTO().getPassword(),
					AuthorityUtils.createAuthorityList(accountToken.getAccountDTO().getRole().toString()));
		} catch (HttpClientErrorException e) {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
