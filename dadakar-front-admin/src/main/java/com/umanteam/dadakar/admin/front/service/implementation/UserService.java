package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.DadakarFrontAdminApplication;
import com.umanteam.dadakar.admin.front.dto.Account;
import com.umanteam.dadakar.admin.front.dto.AccountUser;
import com.umanteam.dadakar.admin.front.dto.Complaint;
import com.umanteam.dadakar.admin.front.dto.Rating;
import com.umanteam.dadakar.admin.front.dto.User;
import com.umanteam.dadakar.admin.front.service.interfaces.IAccountService;
import com.umanteam.dadakar.admin.front.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IAccountService accountService;
		
	@Value("${user.path}")
	private String userPath;
	
	@Value("${rating.base}")
	private int ratingBase;
	
	@Value("${rating.minacceptable}")
	private int ratingMinAcceptable;
	
	private AccountUser userToAccountUser(User user) {
		AccountUser accountUser = new AccountUser();
		BeanUtils.copyProperties(user, accountUser);
		Account account = accountService.findById(user.getAccountId());
		if(account == null) account = new Account();
		accountUser.setAccount(account);
		return accountUser;
	}
	
	private User accountUserToUser(AccountUser accountUser) {
		User user = new User();
		BeanUtils.copyProperties(accountUser, user);
		user.setAccountId(accountUser.getAccount().getAccountId());
		return user;
	}
	
	@Override
	public List<AccountUser> findAll() {
		List <AccountUser> accountUsers = new ArrayList<>();
		List<User> users = new ArrayList<>();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.getToken());
		HttpEntity<List<User>> request = new HttpEntity<>(users, headers);
		ResponseEntity<List<User>> usersResponse = restTemplate.exchange(userPath, HttpMethod.GET, request, new ParameterizedTypeReference<List<User>>() {});
		users = usersResponse.getBody();
		if(users != null) for(User user: users) accountUsers.add(userToAccountUser(user));
		return accountUsers;
	}
	
	@Override
	public AccountUser findById(String id) {
		AccountUser accountUser = new AccountUser();
		User user = new User();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.getToken());
		HttpEntity<User> userRequest = new HttpEntity<>(user, headers);
		ResponseEntity<User> userResponse = restTemplate.exchange(userPath + "/" + id, HttpMethod.GET, userRequest, User.class);
		user = userResponse.getBody();
		if(user != null) accountUser = userToAccountUser(user);
		return accountUser;
	}
	
	@Override
	public AccountUser findByAccountId(String accountId) {
		AccountUser accountUser = new AccountUser();
		User user = new User();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.getToken());
		HttpEntity<User> userRequest = new HttpEntity<>(user, headers);
		ResponseEntity<User> userResponse = restTemplate.exchange(userPath + "/accountid:" + accountId, HttpMethod.GET, userRequest, User.class);
		user = userResponse.getBody();
		if(user != null) accountUser = userToAccountUser(user);
		return accountUser;
	}
	
	@Override
	public AccountUser update(AccountUser accountUser) {
		User user = accountUserToUser(accountUser);
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", DadakarFrontAdminApplication.getToken());
		HttpEntity<User> userRequest = new HttpEntity<>(user, headers);
		ResponseEntity<User> userResponse = restTemplate.exchange(userPath + "/update", HttpMethod.PUT, userRequest, User.class);
		user = userResponse.getBody();
		accountUser = userToAccountUser(user);
		return accountUser;
	}
	
	@Override
	public List<Complaint> getComplaint() {
		List<Complaint> complaints = new ArrayList<>();
		List<AccountUser> accountUsers = findAll();
		if(accountUsers != null) {
			for(AccountUser accountUser: accountUsers) {
				int counter = 0;
				int nbRatings = accountUser.getRatings().size();
				if(nbRatings > ratingBase) for(Rating rating: accountUser.getRatings()) if(rating.getValue() < ratingMinAcceptable) counter++;
				double ratio = nbRatings == 0 ? 0 : (counter / nbRatings);
				if(ratio > 0.5) complaints.add(new Complaint(accountUser, nbRatings, ratio));
			}
		}
		return complaints;
	}

}
