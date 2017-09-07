package com.umanteam.dadakar.admin.front.service.implementation;

import java.util.ArrayList;
import java.util.List;

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
		List<Rating> ratings = new ArrayList<>();
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI1M2MxMzgxYi1mMGMzLTQ0NjQtYmZmYy0wOGUwYmY4YTZkMDMiLCJzdWIiOiJ1c2VybmFtZTAiLCJpYXQiOjE1MDQ3Nzg5MjgsImV4cCI6MzAwMTUwNDc3ODkyOH0.R048wuBYFIRNvylyz1SoqIysxOvPK5q8ddwxSKxgCU2hfd2ROCJ6_UVM5sznisYrjUFSHNWg7sN_Rg_3aZKb6A");
		HttpEntity<List<Rating>> request = new HttpEntity<>(ratings, headers);
		ResponseEntity<List<Rating>> ratingsResponse = restTemplate.exchange(ratingPath, HttpMethod.GET, request, new ParameterizedTypeReference<List<Rating>>() {});
		ratings = ratingsResponse.getBody();
		return ratings;
	}

}
