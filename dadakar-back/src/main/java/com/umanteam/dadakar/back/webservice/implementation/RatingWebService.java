package com.umanteam.dadakar.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umanteam.dadakar.back.dto.RatingDTO;
import com.umanteam.dadakar.back.service.interfaces.IRatingService;
import com.umanteam.dadakar.back.webservice.interfaces.IRatingWebService;

@RestController
@RequestMapping("${appli.path}/ratings")
@CrossOrigin(origins="*")
public class RatingWebService implements IRatingWebService {
	
	@Autowired
	private IRatingService ratingService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public RatingDTO add(@RequestBody RatingDTO ratingDTO) { // OK
		return ratingService.addOrUpdate(ratingDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public RatingDTO update(@RequestBody RatingDTO ratingDTO) { // OK
		return ratingService.addOrUpdate(ratingDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) { // OK
		ratingService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<RatingDTO>> findAll() { // OK
		List<RatingDTO> ratings = ratingService.findAll();
		if(ratings.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<RatingDTO>>(ratings, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public RatingDTO findById(@PathVariable("id") String id) { // OK
		return ratingService.findById(id);
	}

}
