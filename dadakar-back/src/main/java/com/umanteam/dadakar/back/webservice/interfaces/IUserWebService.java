package com.umanteam.dadakar.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.dto.UserDTO;

public interface IUserWebService {
	UserDTO add(UserDTO userDTO);
	UserDTO update(UserDTO userDTO);
	void delete(String id);
	ResponseEntity<List<UserDTO>> findAll();
	UserDTO findById(String id);
	ResponseEntity<List<UserDTO>> findByLastName(String lastName);
	UserDTO findByAccountId(String id);
	UserDTO findByAccountUsername(String username);
}
