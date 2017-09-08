package com.umanteam.dadakar.run.back.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SecurityWebService implements ISecurityWebService {

	@GetMapping("authenticate")
	public void authenticate() {
		
	}
	
}
