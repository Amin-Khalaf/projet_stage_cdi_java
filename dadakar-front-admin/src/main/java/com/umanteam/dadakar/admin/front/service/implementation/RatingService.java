package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.admin.front.dto.Rating;
import com.umanteam.dadakar.admin.front.service.interfaces.IRatingService;

@Service
public class RatingService implements IRatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${rating.path}")
	private String ratingPath;
	
	@Override
	public List<Rating> findAll() {
		ResponseEntity<List<Rating>> ratingsResponse = restTemplate.exchange(ratingPath, HttpMethod.GET, null, new ParameterizedTypeReference<List<Rating>>() {});
		List<Rating> ratings = ratingsResponse.getBody();
		return ratings;
	}

}
