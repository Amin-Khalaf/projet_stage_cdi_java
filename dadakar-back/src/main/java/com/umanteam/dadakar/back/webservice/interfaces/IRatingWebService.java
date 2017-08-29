package com.umanteam.dadakar.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.RatingDTO;

public interface IRatingWebService {
	RatingDTO add(RatingDTO ratingDTO);
	RatingDTO update(RatingDTO ratingDTO);
	void delete(String id);
	ResponseEntity<List<RatingDTO>> findAll();
	ResponseEntity<RatingDTO> findById(String id);
}
