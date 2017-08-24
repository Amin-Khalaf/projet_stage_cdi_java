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
	private RestTemplate restTemplate;
	
	@Autowired
	private IPassengerService passengerService;
	
//	@Value("${server.path}")
//	private String serverPath;
//	
//	@Value("${appli.path}")
//	private String appliPath;
//	
//	private String url = serverPath + appliPath + "/users/";
	private String url = "http://localhost:8080/dadakar/users/";

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

	@RequestMapping(value="/user:{id}", method=RequestMethod.GET)
	@Override
	public PassengerDTO findByUserId(@PathVariable("id") String id) { //KO
		ResponseEntity<UserDTO> userEntity = restTemplate.getForEntity(url + id, UserDTO.class);
		UserDTO userDTO = userEntity.getBody();
		return passengerService.findByUser(userDTO);
	}

}
