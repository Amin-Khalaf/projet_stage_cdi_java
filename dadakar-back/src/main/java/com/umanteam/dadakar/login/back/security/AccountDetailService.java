package com.umanteam.dadakar.login.back.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.login.back.dto.AccountDTO;
import com.umanteam.dadakar.login.back.enums.Role;
import com.umanteam.dadakar.login.back.service.interfaces.IAccountService;

@Service
public class AccountDetailService implements UserDetailsService {
	
	private final IAccountService accountService;

	public AccountDetailService(IAccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AccountDTO account = accountService.findByUsername(username);
		if(account == null || account.getAccountId().equals("")) throw new UsernameNotFoundException("L'utilisateur n'existe pas.");
		
		List<Role> authorithies = new ArrayList<>();
		authorithies.add(account.getRole());
		
		UserDetails userDetails =  User
				.withUsername(username)
				.password(account.getPassword())
				.authorities(authorithies)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
		
		System.out.println(userDetails);
		
		return userDetails;
		
		
	}

}
