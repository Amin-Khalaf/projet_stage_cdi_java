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
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IPassengerService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IPassengerWebService;

@RestController
@RequestMapping("${appli.path}/passengers")
@CrossOrigin(origins="*")
public class PassengerWebService implements IPassengerWebService {
	
	@Autowired
	private IPassengerService passengerService;
	
@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public PassengerDTO add(@RequestBody PassengerDTO passengerDTO) { // OK
		return passengerService.addOrUpdate(passengerDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public PassengerDTO update(@RequestBody PassengerDTO passengerDTO) { // OK
		return passengerService.addOrUpdate(passengerDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) { // OK
		passengerService.delete(id);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<PassengerDTO>> findAll() { // OK
		List<PassengerDTO> passengers = passengerService.findAll();
		if(passengers.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<PassengerDTO>>(passengers, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<PassengerDTO> findById(@PathVariable("id") String id) { // OK
		PassengerDTO passengerDTO = passengerService.findById(id);
		if(passengerDTO.getPassengerId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<PassengerDTO>(passengerDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user:{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<PassengerDTO>> findByUserId(@PathVariable("id") String id) { // OK
		List<PassengerDTO> passengerDTOs = passengerService.findByUserId(id);
		if(passengerDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<PassengerDTO>>(passengerDTOs, HttpStatus.OK);
	}

}
