package com.umanteam.dadakar.back.full.service.interfaces;


import java.util.List;

import com.umanteam.dadakar.back.full.dto.RatingDTO;

public interface IRatingService {
	RatingDTO addOrUpdate(RatingDTO ratingDTO);
	void delete(String id);
	List<RatingDTO> findAll();
	RatingDTO findById(String id);
}
