package com.umanteam.dadakar.back.webservice.implementation;

import java.util.Collections;
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
import com.umanteam.dadakar.back.dto.AccountTokenDTO;
import com.umanteam.dadakar.back.dto.DetailDTO;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/login")
	@Override
	public ResponseEntity<AccountTokenDTO> authorize(@Valid @RequestBody AccountDTO loginAccount) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginAccount.getUsername(), loginAccount.getPassword());
		try {
			authenticationManager.authenticate(authenticationToken);
			AccountDTO accountDTO = accountService.findByUsername(loginAccount.getUsername());
			AccountTokenDTO accountToken = new AccountTokenDTO(accountDTO,  tokenProvider.createToken(loginAccount.getUsername()));
			return new ResponseEntity<AccountTokenDTO>(accountToken, HttpStatus.OK);
		} catch (AuthenticationException e) {
			// TODO: log security error for admin
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/details/{username}",method=RequestMethod.GET)
	@Override
	public ResponseEntity<DetailDTO> getDetails(@PathVariable("username") String username) {
		UserDetails details = detailService.loadUserByUsername(username);
		if(details != null) {
			DetailDTO detail = new DetailDTO(details.getUsername(), details.getPassword(), Collections.emptyList(), !details.isAccountNonExpired(), !details.isAccountNonLocked(), !details.isCredentialsNonExpired(), !details.isEnabled());
			return new ResponseEntity<DetailDTO>(detail, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.UNAUTHORIZED);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/signup")
	@Override
	public ResponseEntity<AccountTokenDTO> signup(@RequestBody AccountDTO signupAccount) {
		if(signupAccount.getRole() != Role.USER) return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
		AccountDTO account = accountService.findByUsername(signupAccount.getUsername());
		if(account != null && !account.getAccountId().equals("")) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		
		account = accountService.addOrUpdate(signupAccount);
		AccountTokenDTO accountToken = new AccountTokenDTO(account, tokenProvider.createToken(signupAccount.getUsername())) ;
		return new ResponseEntity<AccountTokenDTO>(accountToken, HttpStatus.OK);
		
	}
}
