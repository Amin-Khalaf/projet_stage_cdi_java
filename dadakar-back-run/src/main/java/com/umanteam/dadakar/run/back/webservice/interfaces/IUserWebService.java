package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.UserDTO;

public interface IUserWebService {
	UserDTO add(UserDTO userDTO);
	UserDTO update(UserDTO userDTO);
	void delete(String id);
	ResponseEntity<List<UserDTO>> findAll();
	ResponseEntity<UserDTO> findById(String id);
	ResponseEntity<List<UserDTO>> findByLastName(String lastName);
	ResponseEntity<UserDTO> findByAccountId(String accountId);
}
