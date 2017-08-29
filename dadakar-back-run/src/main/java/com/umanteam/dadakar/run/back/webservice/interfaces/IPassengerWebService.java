package com.umanteam.dadakar.run.back.webservice.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.umanteam.dadakar.run.back.dto.PassengerDTO;

public interface IPassengerWebService {
	PassengerDTO add(PassengerDTO passengerDTO);
	PassengerDTO update(PassengerDTO passengerDTO);
	void delete(String id);
	ResponseEntity<List<PassengerDTO>> findAll();
	ResponseEntity<PassengerDTO> findById(String id);
	ResponseEntity<List<PassengerDTO>> findByUserId(String id);
}
