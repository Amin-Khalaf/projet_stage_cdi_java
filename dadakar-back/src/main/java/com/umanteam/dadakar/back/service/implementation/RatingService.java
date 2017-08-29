package com.umanteam.dadakar.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.RatingDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.entities.Rating;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.repository.RatingRepository;
import com.umanteam.dadakar.back.service.interfaces.IRatingService;

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
		List<Rating> ratings= ratingRepository.findAll();
		if(ratings != null) for(Rating rating: ratings) ratingDTOs.add(ratingToRatingDTO(rating));
		return ratingDTOs;
	}

	@Override
	public RatingDTO findById(String id) {
		Rating rating = ratingRepository.findOne(id);
		if(rating != null) return ratingToRatingDTO(rating);
		return new RatingDTO();
	}

}
