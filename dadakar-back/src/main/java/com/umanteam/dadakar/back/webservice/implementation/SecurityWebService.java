package com.umanteam.dadakar.back.webservice.implementation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.security.TokenProvider;
import com.umanteam.dadakar.back.service.interfaces.IAccountService;

@RestController
@CrossOrigin("*")
public class SecurityWebService {
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private TokenProvider tokenProvider;
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/authenticate")
	public void authenticate() {
		
	}
	
	@PostMapping("/login")
		public String authorize(@Valid @RequestBody AccountDTO loginAccount, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginAccount.getUsername(), loginAccount.getPassword());
		try {
			authenticationManager.authenticate(authenticationToken);
			return tokenProvider.createToken(loginAccount.getUsername());
		} catch (AuthenticationException e) {
			// TODO: log error
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
	}
	
	@PostMapping("signup")
	public String signup(@RequestBody AccountDTO signupAccount) {
		AccountDTO account = accountService.findByUsername(signupAccount.getUsername());
		if(account != null && !account.getAccountId().equals("")) {
			return "EXISTS";
		}
		
		accountService.addOrUpdate(signupAccount);
		return tokenProvider.createToken(signupAccount.getUsername());
		
	}

}
