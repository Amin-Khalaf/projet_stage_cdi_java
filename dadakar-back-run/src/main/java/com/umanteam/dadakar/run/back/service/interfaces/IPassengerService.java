package com.umanteam.dadakar.run.back.service.interfaces;

import java.util.List;

import com.umanteam.dadakar.run.back.dto.PassengerDTO;

public interface IPassengerService {
	PassengerDTO addOrUpdate(PassengerDTO passengerDTO);
	void delete(String id);
	List<PassengerDTO> findAll();
	PassengerDTO findById(String id);
	List<PassengerDTO> findByUserId(String id);
}
