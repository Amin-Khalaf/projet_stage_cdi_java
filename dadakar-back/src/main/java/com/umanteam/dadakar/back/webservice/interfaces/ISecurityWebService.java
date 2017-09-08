package com.umanteam.dadakar.back.webservice.interfaces;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.Detail;

public interface ISecurityWebService {
	void authenticate();
	String authorize(AccountDTO loginAccount, HttpServletResponse response);
	ResponseEntity<Detail> getDetails(String username);
	String signup(AccountDTO signupAccount);
}
