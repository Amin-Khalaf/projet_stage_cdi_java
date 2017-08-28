package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.RatingDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.Rating;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
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
		if(userDTO.getVehicles() != null) {
			for(VehicleDTO vehicleDTO: userDTO.getVehicles()) {
				Vehicle vehicle = new Vehicle();
				BeanUtils.copyProperties(vehicleDTO, vehicle);
				vehicles.add(vehicle);
			}
		}
		user.setVehicles(vehicles);
		if(userDTO.getRatings() != null) {
			for(RatingDTO ratingDTO: userDTO.getRatings()) {
				Rating rating = new Rating();
				BeanUtils.copyProperties(ratingDTO, rating);
				ratings.add(rating);
			}
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
		for(Passenger passenger: passengerRepository.findAll()) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}

	@Override
	public PassengerDTO findById(String id) {
		return passengerToPassengerDTO(passengerRepository.findOne(id));
	}

	@Override
	public List<PassengerDTO> findByUser(UserDTO userDTO) {
		List<PassengerDTO> passengerDTOs = new ArrayList<>();
		for(Passenger passenger: passengerRepository.findByUser(userDTOToUser(userDTO))) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}

}
