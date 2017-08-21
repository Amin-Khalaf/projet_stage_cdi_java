package com.umanteam.dadakar.back.service.interfaces;


import java.util.List;

import com.umanteam.dadakar.back.dto.RatingDTO;

public interface IRatingService {
	RatingDTO add(RatingDTO ratingDTO);
	RatingDTO update(RatingDTO ratingDTO);
	void delete(String id);
	List<RatingDTO> findAll();
	RatingDTO findById(String id);
}
