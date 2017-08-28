package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.RatingDTO;
import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.entities.Rating;
import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.repository.RatingRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IRatingService;

@Service("ratingService")
public class RatingService implements IRatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	/* copy from RatingDTO to Rating */
	private Rating ratingDTOToRating(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		User rater = new User();
		BeanUtils.copyProperties(ratingDTO, rating);
		BeanUtils.copyProperties(ratingDTO.getRater(), rater);
		rating.setRater(rater);
		return rating;
	}
	
	/* copy from Rating to RatingDTO */
	private RatingDTO ratingToRatingDTO(Rating rating) {
		RatingDTO ratingDTO = new RatingDTO();
		UserDTO raterDTO = new UserDTO();
		BeanUtils.copyProperties(rating, ratingDTO);
		BeanUtils.copyProperties(rating.getRater(), raterDTO);
		ratingDTO.setRater(raterDTO);
		return ratingDTO;
	}
	
	@Override
	public RatingDTO addOrUpdate(RatingDTO ratingDTO) {
		return ratingToRatingDTO(ratingRepository.save(ratingDTOToRating(ratingDTO)));
	}

	@Override
	public void delete(String id) {
		ratingRepository.delete(id);
	}

	@Override
	public List<RatingDTO> findAll() {
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		for(Rating rating: ratingRepository.findAll()) ratingDTOs.add(ratingToRatingDTO(rating));
		return ratingDTOs;
	}

	@Override
	public RatingDTO findById(String id) {
		return ratingToRatingDTO(ratingRepository.findOne(id));
	}

}
