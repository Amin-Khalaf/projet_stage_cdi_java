package com.umanteam.dadakar.run.back.service.interfaces;


import java.util.List;

import com.umanteam.dadakar.run.back.dto.RatingDTO;

public interface IRatingService {
	RatingDTO addOrUpdate(RatingDTO ratingDTO);
	void delete(String id);
	List<RatingDTO> findAll();
	RatingDTO findById(String id);
}
