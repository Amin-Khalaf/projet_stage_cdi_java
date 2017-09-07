package com.umanteam.dadakar.msg.back.webservice.implementation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SecurityWebService {

	@GetMapping("authenticate")
	public void authenticate() {
		
	}
	
}
