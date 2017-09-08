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

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.service.interfaces.IUserService;
import com.umanteam.dadakar.back.webservice.interfaces.IUserWebService;

@RestController
@RequestMapping("${appli.path}/users")
@CrossOrigin(origins="*")
public class UserWebService implements IUserWebService {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@Override
	public UserDTO add(@RequestBody UserDTO userDTO) { // OK
		return userService.addOrUpdate(userDTO);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	@Override
	public UserDTO update(@RequestBody UserDTO userDTO) { // OK
		return userService.addOrUpdate(userDTO);
	}

	@RequestMapping(value="/del/{id}", method=RequestMethod.DELETE)
	@Override
	public void delete(@PathVariable("id") String id) { // OK
		userService.delete(id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<UserDTO>> findAll() { // OK
		List<UserDTO> users = userService.findAll();
		if(users == null || users.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) { // OK
		UserDTO userDTO = userService.findById(id);
		if(userDTO == null || userDTO.getUserId() == null || userDTO.getUserId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/name:{lastName}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<List<UserDTO>> findByLastName(@PathVariable("lastName") String lastName) { // OK
		List<UserDTO> users = userService.findByLastName(lastName);
		if(users == null || users.isEmpty()) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/username:{username}", method=RequestMethod.GET)
	@Override
	public ResponseEntity<UserDTO> findByAccountUsername(@PathVariable("username") String username) {
		UserDTO userDTO = userService.findByAccountUsername(username);
		if(userDTO == null || userDTO.getUserId() == null || userDTO.getUserId().equals("")) return new ResponseEntity(HttpStatus.NO_CONTENT);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

}
