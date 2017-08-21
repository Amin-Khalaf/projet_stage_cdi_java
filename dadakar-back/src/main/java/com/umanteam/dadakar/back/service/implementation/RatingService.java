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
	
	@Override
	public RatingDTO add(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		User rater = new User();
		BeanUtils.copyProperties(ratingDTO, rating);
		BeanUtils.copyProperties(ratingDTO.getRater(), rater);
		rating.setRater(rater);
		rating = ratingRepository.insert(rating);
		UserDTO raterDTO = new UserDTO();
		BeanUtils.copyProperties(rating, ratingDTO);
		BeanUtils.copyProperties(rating.getRater(), raterDTO);
		ratingDTO.setRater(raterDTO);
		return ratingDTO;
	}

	@Override
	public RatingDTO update(RatingDTO ratingDTO) {
		Rating rating = new Rating();
		User rater = new User();
		BeanUtils.copyProperties(ratingDTO, rating);
		BeanUtils.copyProperties(ratingDTO.getRater(), rater);
		rating.setRater(rater);
		rating = ratingRepository.save(rating);
		UserDTO raterDTO = new UserDTO();
		BeanUtils.copyProperties(rating, ratingDTO);
		BeanUtils.copyProperties(rating.getRater(), raterDTO);
		ratingDTO.setRater(raterDTO);
		return ratingDTO;
	}

	@Override
	public void delete(String id) {
		ratingRepository.delete(id);
	}

	@Override
	public List<RatingDTO> findAll() {
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		for(Rating rating: ratingRepository.findAll()) {
			RatingDTO ratingDTO = new RatingDTO();
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(rating, ratingDTO);
			BeanUtils.copyProperties(rating.getRater(), userDTO);
			ratingDTO.setRater(userDTO);
			ratingDTOs.add(ratingDTO);
		}
		return ratingDTOs;
	}

	@Override
	public RatingDTO findById(String id) {
		RatingDTO ratingDTO = new RatingDTO();
		Rating rating = ratingRepository.findOne(id);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(rating, ratingDTO);
		BeanUtils.copyProperties(rating.getRater(), userDTO);
		ratingDTO.setRater(userDTO);
		return ratingDTO;
	}

}
