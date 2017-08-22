package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.repository.UserRepository;
import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IPassengerService;

@Service("passengerService")
public class PassengerService implements IPassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public PassengerDTO add(PassengerDTO passengerDTO) {
		Passenger passenger = new Passenger();
		User user = new User();
		WayPoint wayPoint = new WayPoint();
		BeanUtils.copyProperties(passengerDTO, passenger);
		BeanUtils.copyProperties(passengerDTO.getUser(), user);
		passenger.setUser(user);
		BeanUtils.copyProperties(passengerDTO.getStartPlace(), wayPoint);
		passenger.setStartPlace(wayPoint);
		wayPoint = new WayPoint();
		BeanUtils.copyProperties(passengerDTO.getEndPlace(), wayPoint);
		passenger.setEndPlace(wayPoint);
		passenger = passengerRepository.insert(passenger);
		UserDTO userDTO = new UserDTO();
		WayPointDTO wayPointDTO = new WayPointDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		BeanUtils.copyProperties(passenger.getUser(), userDTO);
		passengerDTO.setUser(userDTO);
		BeanUtils.copyProperties(passenger.getStartPlace(), wayPointDTO);
		passengerDTO.setStartPlace(wayPointDTO);
		wayPointDTO = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getEndPlace(), wayPointDTO);
		passengerDTO.setEndPlace(wayPointDTO);
		return passengerDTO;
	}

	@Override
	public PassengerDTO update(PassengerDTO passengerDTO) {
		Passenger passenger = new Passenger();
		User user = new User();
		WayPoint wayPoint = new WayPoint();
		BeanUtils.copyProperties(passengerDTO, passenger);
		BeanUtils.copyProperties(passengerDTO.getUser(), user);
		passenger.setUser(user);
		BeanUtils.copyProperties(passengerDTO.getStartPlace(), wayPoint);
		passenger.setStartPlace(wayPoint);
		wayPoint = new WayPoint();
		BeanUtils.copyProperties(passengerDTO.getEndPlace(), wayPoint);
		passenger.setEndPlace(wayPoint);
		passenger = passengerRepository.save(passenger);
		UserDTO userDTO = new UserDTO();
		WayPointDTO wayPointDTO = new WayPointDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		BeanUtils.copyProperties(passenger.getUser(), userDTO);
		passengerDTO.setUser(userDTO);
		BeanUtils.copyProperties(passenger.getStartPlace(), wayPointDTO);
		passengerDTO.setStartPlace(wayPointDTO);
		wayPointDTO = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getEndPlace(), wayPointDTO);
		passengerDTO.setEndPlace(wayPointDTO);
		return passengerDTO;
	}

	@Override
	public void delete(String id) {
		passengerRepository.delete(id);
	}

	@Override
	public List<PassengerDTO> findAll() {
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		for(Passenger passenger: passengerRepository.findAll()) {
			PassengerDTO passengerDTO = new PassengerDTO();
			BeanUtils.copyProperties(passenger, passengerDTO);
			UserDTO user = new UserDTO();
			BeanUtils.copyProperties(passenger.getUser(), user);
			passengerDTO.setUser(user);
			WayPointDTO wayPoint = new WayPointDTO();
			BeanUtils.copyProperties(passenger.getStartPlace(), wayPoint);
			passengerDTO.setStartPlace(wayPoint);
			wayPoint = new WayPointDTO();
			BeanUtils.copyProperties(passenger.getEndPlace(), wayPoint);
			passengerDTO.setEndPlace(wayPoint);
			passengerDTOs.add(passengerDTO);
		}
		return passengerDTOs;
	}

	@Override
	public PassengerDTO findById(String id) {
		Passenger passenger = passengerRepository.findOne(id);
		PassengerDTO passengerDTO = new PassengerDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		UserDTO user = new UserDTO();
		BeanUtils.copyProperties(passenger.getUser(), user);
		passengerDTO.setUser(user);
		WayPointDTO wayPoint = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getStartPlace(), wayPoint);
		passengerDTO.setStartPlace(wayPoint);
		wayPoint = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getEndPlace(), wayPoint);
		passengerDTO.setEndPlace(wayPoint);
		return passengerDTO;
	}

	@Override
	public PassengerDTO findByUser(UserDTO userDTO) {
		User user = userRepository.findOne(userDTO.getUserId());
		Passenger passenger = passengerRepository.findByUser(user);
		PassengerDTO passengerDTO = new PassengerDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		userDTO = new UserDTO();
		BeanUtils.copyProperties(passenger.getUser(), userDTO);
		passengerDTO.setUser(userDTO);
		WayPointDTO wayPoint = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getStartPlace(), wayPoint);
		passengerDTO.setStartPlace(wayPoint);
		wayPoint = new WayPointDTO();
		BeanUtils.copyProperties(passenger.getEndPlace(), wayPoint);
		passengerDTO.setEndPlace(wayPoint);
		return passengerDTO;
	}

}
