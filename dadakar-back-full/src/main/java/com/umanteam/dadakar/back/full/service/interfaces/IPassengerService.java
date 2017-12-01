package com.umanteam.dadakar.back.full.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.back.full.dto.PassengerDTO;
import com.umanteam.dadakar.back.full.dto.UserDTO;

public interface IPassengerService {
	PassengerDTO addOrUpdate(PassengerDTO passengerDTO);
	void delete(String id);
	List<PassengerDTO> findAll();
	PassengerDTO findById(String id);
	List<PassengerDTO> findByUser(UserDTO userDTO);
}
