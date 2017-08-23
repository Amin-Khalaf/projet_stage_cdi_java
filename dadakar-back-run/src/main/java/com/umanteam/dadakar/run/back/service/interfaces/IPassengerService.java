package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;

public interface IPassengerService {
	PassengerDTO add(PassengerDTO passengerDTO);
	PassengerDTO update(PassengerDTO passengerDTO);
	void delete(String id);
	List<PassengerDTO> findAll();
	PassengerDTO findById(String id);
	PassengerDTO findByUser(UserDTO userDTO);
}
