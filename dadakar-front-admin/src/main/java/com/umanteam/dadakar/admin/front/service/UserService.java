package com.umanteam.dadakar.admin.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.User;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${user.path}")
	private String userPath;
	
	public List<User> findAll() {
		ResponseEntity<List<User>> usersResponse = restTemplate.exchange(userPath, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
		List<User> users = usersResponse.getBody();
		return users;
	}
	
	public User findById(String id) {
		ResponseEntity<User> userResponse = restTemplate.getForEntity(userPath, User.class);
		User user = userResponse.getBody();
		return user;
	}
	
	public User update(User user) {
		HttpEntity<User> userRequest = new HttpEntity<>(user);
		ResponseEntity<User> userResponse = restTemplate.exchange(userPath + "/update", HttpMethod.PUT, userRequest, User.class);
		user = userResponse.getBody();
		return user;
	}

}
