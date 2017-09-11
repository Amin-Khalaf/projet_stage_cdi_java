package com.umanteam.dadakar.run.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.run.back.dto.PassengerDTO;
import com.umanteam.dadakar.run.back.dto.RatingDTO;
import com.umanteam.dadakar.run.back.dto.UserDTO;
import com.umanteam.dadakar.run.back.dto.VehicleDTO;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Rating;
import com.umanteam.dadakar.run.back.entities.User;
import com.umanteam.dadakar.run.back.entities.Vehicle;
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
		User user = new User();
		BeanUtils.copyProperties(passengerDTO.getUser(), user);
		List<RatingDTO> ratingDTOs = passengerDTO.getUser().getRatings();
		List<Rating> ratings = new ArrayList<>();
		if(ratingDTOs != null) {
			for(RatingDTO ratingDTO: ratingDTOs) {
				Rating rating = new Rating();
				BeanUtils.copyProperties(ratingDTO, rating);
				ratings.add(rating);
			}
		}
		user.setRatings(ratings);
		List<VehicleDTO> vehicleDTOs = passengerDTO.getUser().getVehicles();
		List<Vehicle> vehicles = new ArrayList<>();
		if(vehicleDTOs != null) {
			for(VehicleDTO vehicleDTO: vehicleDTOs) {
				Vehicle vehicle = new Vehicle();
				BeanUtils.copyProperties(vehicleDTO, vehicle);
				vehicles.add(vehicle);
			}
		}
		user.setVehicles(vehicles);
		passenger.setUser(user);
		return passenger;
	}
	
	/* copy form Passenger to PassengerDTO */
	private PassengerDTO passengerToPassengerDTO(Passenger passenger) {
		PassengerDTO passengerDTO = new PassengerDTO();
		BeanUtils.copyProperties(passenger, passengerDTO);
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(passenger.getUser(), userDTO);
		passengerDTO.setUser(userDTO);
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
		List<Passenger> passengers = passengerRepository.findByUserUserId(userId);
		if(passengers != null) for(Passenger passenger: passengers) passengerDTOs.add(passengerToPassengerDTO(passenger));
		return passengerDTOs;
	}

}
