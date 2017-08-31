package com.umanteam.dadakar.admin.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.Rating;

@Service
public class RatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${rating.path}")
	private String ratingPath;
	
	public List<Rating> findAll() {
		ResponseEntity<List<Rating>> ratingsResponse = restTemplate.exchange(ratingPath, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {});
		List<Rating> ratings = ratingsResponse.getBody();
		return ratings;
	}

}
