package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${account.path}")
	private String accountPath;

	@Override
	public Account add(Account account) {
		String url = accountPath + "/save";
		HttpEntity<Account> request = new HttpEntity<Account>(account);
		ResponseEntity<Account> answer = restTemplate.exchange(url, HttpMethod.POST, request, Account.class);
		account = answer.getBody();
		return account;
	}

	@Override
	public Account update(Account account) {
		String url = accountPath + "/update";
		HttpEntity<Account> request = new HttpEntity<Account>(account);
		ResponseEntity<Account> answer = restTemplate.exchange(url, HttpMethod.PUT, request, Account.class);
		account = answer.getBody();
		return account;
	}

	@Override
	public void delete(String id) {
		String url = accountPath + "/del/" + id;
		restTemplate.delete(url);
	}

	@Override
	public List<Account> findAll() {
		ResponseEntity<List<Account>> answer = restTemplate.exchange(accountPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Account>>() {
				});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public Account findById(String id) {
		String url = accountPath + "/" + id;
		ResponseEntity<Account> answer = restTemplate.getForEntity(url, Account.class);
		Account account = answer.getBody();
		return 	account;
	}

	@Override
	public Account findByUsername(String username) {
		String url = accountPath + "/username:" + username;
		ResponseEntity<Account> answer = restTemplate.getForEntity(url, Account.class);
		Account account = answer.getBody();
		return 	account;
	}

	@Override
	public List<Account> findUsers() {
		String url = accountPath + "/users";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findAdmins() {
		String url = accountPath + "/admins";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findSuperUsers() {
		String url = accountPath + "/superusers";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findAdminsAndSuperUsers() {
		String url = accountPath + "/adminsandsuperusers";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findBanned() {
		String url = accountPath + "/banned";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findNotBanned() {
		String url = accountPath + "/notbanned";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findDeleted() {
		String url = accountPath + "/deleted";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findUsersDeleted() {
		String url = accountPath + "/deleted:users";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findAdminsDeleted() {
		String url = accountPath + "/deleted:admins";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findNotDeleted() {
		String url = accountPath + "/notdeleted";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findUsersNotDeleted() {
		String url = accountPath + "/notdeleted:users";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}

	@Override
	public List<Account> findAdminsNotDeleted() {
		String url = accountPath + "/notdeleted:admins";
		ResponseEntity<List<Account>> answer = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {
		});
		List<Account> accounts = answer.getBody();
		return accounts;
	}
	
	
}
