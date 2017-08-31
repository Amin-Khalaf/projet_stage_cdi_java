package com.umanteam.dadakar.run.back.webservice.implementation;

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

import com.umanteam.dadakar.run.back.dto.TollDTO;
import com.umanteam.dadakar.run.back.service.interfaces.ITollService;
import com.umanteam.dadakar.run.back.webservice.interfaces.ITollWebService;

@RestController
@RequestMapping("${appli.path}/tolls")
@CrossOrigin(origins="*")
public class TollWebService implements ITollWebService {
	
	@Autowired
	private ITollService tollService;

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public TollDTO add(@RequestBody TollDTO tollDTO) {
		return tollService.addOrUpdate(tollDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public TollDTO update(@RequestBody TollDTO tollDTO) {
		return tollService.addOrUpdate(tollDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) {
		tollService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<TollDTO>> findAll() {
		List<TollDTO> tollDTOs = tollService.findAll();
		if(tollDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TollDTO>>(tollDTOs, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseEntity<TollDTO> findById(String id) {
		TollDTO tollDTO = tollService.findById(id);
		if(tollDTO.getTollId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TollDTO>(tollDTO, HttpStatus.OK);
	}

}
