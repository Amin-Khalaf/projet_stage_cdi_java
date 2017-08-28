package com.umanteam.dadakar.run.back.webservice.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.service.interfaces.IPassengerService;
import com.umanteam.dadakar.run.back.webservice.interfaces.IPassengerWebService;

@RestController
@RequestMapping("${appli.path}/passengers")
@CrossOrigin(origins="*")
public class PassengerWebService implements IPassengerWebService {
	
	@Autowired
	private IPassengerService passengerService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${users.path}")
	private String usersUrl;
	
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public PassengerDTO findById(@PathVariable("id") String id) { // OK
		return passengerService.findById(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user:{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<PassengerDTO>> findByUserId(@PathVariable("id") String id) { // OK
		UserDTO userDTO = restTemplate.getForEntity(usersUrl + id, UserDTO.class).getBody();
		System.out.println(">>> " + userDTO);
		List<PassengerDTO> passengerDTOs = passengerService.findByUser(userDTO);
		if(passengerDTOs.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<PassengerDTO>>(passengerDTOs, HttpStatus.OK);
	}

}
