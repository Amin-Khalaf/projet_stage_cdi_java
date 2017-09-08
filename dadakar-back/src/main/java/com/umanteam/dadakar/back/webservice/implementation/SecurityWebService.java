package com.umanteam.dadakar.back.webservice.implementation;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.Detail;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.back.security.AccountDetailService;
import com.umanteam.dadakar.back.security.TokenProvider;
import com.umanteam.dadakar.back.service.interfaces.IAccountService;
import com.umanteam.dadakar.back.webservice.interfaces.ISecurityWebService;

@RestController
@CrossOrigin("*")
public class SecurityWebService implements ISecurityWebService {
	
	@Autowired
	private AccountDetailService detailService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private TokenProvider tokenProvider;
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/authenticate")
	@Override
	public void authenticate() {
		
	}
	
	@PostMapping("/login")
	@Override
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/details/{username}",method=RequestMethod.GET)
	@Override
	public ResponseEntity<Detail> getDetails(@PathVariable("username") String username) {
		UserDetails details = detailService.loadUserByUsername(username);
		if(details != null) {
			Detail detail = new Detail(details.getUsername(), details.getPassword(), details.getAuthorities(), !details.isAccountNonExpired(), !details.isAccountNonLocked(), !details.isCredentialsNonExpired(), !details.isEnabled());
			return new ResponseEntity<Detail>(detail, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}
	
	@PostMapping("signup")
	@Override
	public String signup(@RequestBody AccountDTO signupAccount) {
		if(signupAccount.getRole() != Role.USER) return "UNVALID ACCOUNT";
		AccountDTO account = accountService.findByUsername(signupAccount.getUsername());
		if(account != null && !account.getAccountId().equals("")) {
			return "EXISTS";
		}
		
		accountService.addOrUpdate(signupAccount);
		return tokenProvider.createToken(signupAccount.getUsername());
		
	}
}
