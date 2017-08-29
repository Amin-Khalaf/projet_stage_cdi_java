package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IPassengerService;

@Service("passengerService")
public class PassengerService implements IPassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	/* copy from PassengerDTO to Passenger */
	private Passenger passengerDTOToPassenger(PassengerDTO passengerDTO) {
		Passenger passenger = new Passenger();
		BeanUtils.copyProperties(passengerDTO, passenger);
		return passenger;
	}
	
	/* copy form Passenger to PassengerDTO */
	private PassengerDTO passengerToPassengerDTO(Passenger passenger) {
		PassengerDTO passengerDTO = new PassengerDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		return passengerDTO;
	}
	
	@Override
	public PassengerDTO addOrUpdate(PassengerDTO passengerDTO) {
		return passengerToPassengerDTO(passengerRepository.save(passengerDTOToPassenger(passengerDTO)));
	}

	@Override
	public void delete(String id) {
		passengerRepository.delete(id);
	}

	@Override
	public List<PassengerDTO> findAll() {
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		List<Passenger> passengers = passengerRepository.findAll();
		if(passengers != null) for(Passenger passenger: passengers) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}

	@Override
	public PassengerDTO findById(String id) {
		Passenger passenger = passengerRepository.findOne(id);
		if(passenger != null) return passengerToPassengerDTO(passenger);
		return new PassengerDTO();
	}

	@Override
	public List<PassengerDTO> findByUserId(String userId) {
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		List<Passenger> passengers = passengerRepository.findByUserId(userId);
		if(passengers != null) for(Passenger passenger: passengers) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}

}
