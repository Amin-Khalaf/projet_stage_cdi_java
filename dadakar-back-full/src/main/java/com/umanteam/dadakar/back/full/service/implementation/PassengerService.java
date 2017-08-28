package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.dto.VehicleDTO;
import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.dto.PassengerDTO;
import com.umanteam.dadakar.back.full.dto.RatingDTO;
import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.entities.Passenger;
import com.umanteam.dadakar.back.full.entities.Rating;
import com.umanteam.dadakar.back.full.repository.PassengerRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IPassengerService;

@Service("passengerService")
public class PassengerService implements IPassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	/* copy from PassengerDTO to Passenger */
	private Passenger passengerDTOToPassenger(PassengerDTO passengerDTO) {
		Passenger passenger = new Passenger();
		User user = new User();
		BeanUtils.copyProperties(passengerDTO, passenger);
		BeanUtils.copyProperties(passengerDTO.getUser(), user);
		passenger.setUser(user);
		return passenger;
	}
	
	/* copy form Passenger to PassengerDTO */
	private PassengerDTO passengerToPassengerDTO(Passenger passenger) {
		PassengerDTO passengerDTO = new PassengerDTO();
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		BeanUtils.copyProperties(passenger.getUser(), userDTO);
		passengerDTO.setUser(userDTO);
		return passengerDTO;
	}
	
	/* copy from UserDTO to User */
	private User userDTOToUser(UserDTO userDTO) {
		User user = new User();
		Account account = new Account();
		List<Vehicle> vehicles = new ArrayList<>();
		List<Rating> ratings = new ArrayList<>();
		BeanUtils.copyProperties(userDTO, user);
		BeanUtils.copyProperties(userDTO.getAccount(), account);
		user.setAccount(account);
		for(VehicleDTO vehicleDTO: userDTO.getVehicles()) {
			Vehicle vehicle = new Vehicle();
			BeanUtils.copyProperties(vehicleDTO, vehicle);
			vehicles.add(vehicle);
		}
		user.setVehicles(vehicles);
		for(RatingDTO ratingDTO: userDTO.getRatings()) {
			Rating rating = new Rating();
			BeanUtils.copyProperties(ratingDTO, rating);
			ratings.add(rating);
		}
		user.setRatings(ratings);
		return user;
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
		else return new PassengerDTO();
	}

	@Override
	public List<PassengerDTO> findByUser(UserDTO userDTO) {
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		List<Passenger> passengers = passengerRepository.findByUser(userDTOToUser(userDTO));
		if(passengers != null) for(Passenger passenger: passengers) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}
	
}
