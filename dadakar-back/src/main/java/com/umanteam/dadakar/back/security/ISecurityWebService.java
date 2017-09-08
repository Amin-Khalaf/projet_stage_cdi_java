package com.umanteam.dadakar.back.security;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.AccountDTO;

public interface ISecurityWebService {
	void authenticate();
	ResponseEntity<AccountTokenDTO> authorize(AccountDTO loginAccount);
	ResponseEntity<DetailDTO> getDetails(String username);
	ResponseEntity<AccountTokenDTO> signup(AccountDTO signupAccount);
}
