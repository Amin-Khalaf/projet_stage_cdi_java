package com.umanteam.dadakar.back.full.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.back.full.dto.UserDTO;

public interface IUserWebService {
	UserDTO add(UserDTO userDTO);
	UserDTO update(UserDTO userDTO);
	void delete(String id);
	ResponseEntity<List<UserDTO>> findAll();
	ResponseEntity<UserDTO> findById(String id);
	ResponseEntity<List<UserDTO>> findByLastName(String lastName);
	ResponseEntity<UserDTO> findByAccountUsername(String username);
}
