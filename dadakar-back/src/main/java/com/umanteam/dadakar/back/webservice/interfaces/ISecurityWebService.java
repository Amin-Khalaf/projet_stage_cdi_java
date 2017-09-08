package com.umanteam.dadakar.back.webservice.interfaces;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.AccountTokenDTO;
import com.umanteam.dadakar.back.dto.DetailDTO;

public interface ISecurityWebService {
	void authenticate();
	ResponseEntity<AccountTokenDTO> authorize(AccountDTO loginAccount);
	ResponseEntity<DetailDTO> getDetails(String username);
	ResponseEntity<AccountTokenDTO> signup(AccountDTO signupAccount);
}
