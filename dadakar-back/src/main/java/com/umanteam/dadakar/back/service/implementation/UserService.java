package com.umanteam.dadakar.back.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.RatingDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.Rating;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.back.repository.UserRepository;
import com.umanteam.dadakar.back.service.interfaces.IUserService;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO add(UserDTO userDTO) {
		User user = new User();
		Account account = new Account();
		AccountDTO accountDTO = new AccountDTO();
		List<Vehicle> vehicles = new ArrayList<>();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		List<Rating> ratings = new ArrayList<>();
		List<RatingDTO> ratingDTOs = new ArrayList<>();
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
		user = userRepository.insert(user);
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getAccount(), accountDTO);
		userDTO.setAccount(accountDTO);
		for(Vehicle vehicle: user.getVehicles()) {
			VehicleDTO vehicleDTO = new VehicleDTO();
			BeanUtils.copyProperties(vehicle, vehicleDTO);
			vehicleDTOs.add(vehicleDTO);
		}
		userDTO.setVehicles(vehicleDTOs);
		for(Rating rating: user.getRatings()) {
			RatingDTO ratingDTO = new RatingDTO();
			BeanUtils.copyProperties(rating, ratingDTO);
			ratingDTOs.add(ratingDTO);
		}
		userDTO.setRatings(ratingDTOs);
		return userDTO;
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		User user = new User();
		Account account = new Account();
		AccountDTO accountDTO = new AccountDTO();
		List<Vehicle> vehicles = new ArrayList<>();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		List<Rating> ratings = new ArrayList<>();
		List<RatingDTO> ratingDTOs = new ArrayList<>();
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
		user = userRepository.save(user);
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getAccount(), accountDTO);
		userDTO.setAccount(accountDTO);
		for(Vehicle vehicle: user.getVehicles()) {
			VehicleDTO vehicleDTO = new VehicleDTO();
			BeanUtils.copyProperties(vehicle, vehicleDTO);
			vehicleDTOs.add(vehicleDTO);
		}
		userDTO.setVehicles(vehicleDTOs);
		for(Rating rating: user.getRatings()) {
			RatingDTO ratingDTO = new RatingDTO();
			BeanUtils.copyProperties(rating, ratingDTO);
			ratingDTOs.add(ratingDTO);
		}
		userDTO.setRatings(ratingDTOs);
		return userDTO;
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> userDTOs = new ArrayList<>();
		for(User user: userRepository.findAll()) {
			UserDTO userDTO = new UserDTO();
			AccountDTO accountDTO = new AccountDTO();
			List<VehicleDTO> vehicleDTOs = new ArrayList<>();
			List<RatingDTO> ratingDTOs = new ArrayList<>();
			BeanUtils.copyProperties(user, userDTO);
			BeanUtils.copyProperties(user.getAccount(), accountDTO);
			userDTO.setAccount(accountDTO);
			for(Vehicle vehicle: user.getVehicles()) {
				VehicleDTO vehicleDTO = new VehicleDTO();
				BeanUtils.copyProperties(vehicle, vehicleDTO);
				vehicleDTOs.add(vehicleDTO);
			}
			userDTO.setVehicles(vehicleDTOs);
			for(Rating rating: user.getRatings()) {
				RatingDTO ratingDTO = new RatingDTO();
				BeanUtils.copyProperties(rating, ratingDTO);
				ratingDTOs.add(ratingDTO);
			}
			userDTO.setRatings(ratingDTOs);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public UserDTO findById(String id) {
		User user = userRepository.findOne(id);
		UserDTO userDTO = new UserDTO();
		AccountDTO accountDTO = new AccountDTO();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getAccount(), accountDTO);
		userDTO.setAccount(accountDTO);
		for(Vehicle vehicle: user.getVehicles()) {
			VehicleDTO vehicleDTO = new VehicleDTO();
			BeanUtils.copyProperties(vehicle, vehicleDTO);
			vehicleDTOs.add(vehicleDTO);
		}
		userDTO.setVehicles(vehicleDTOs);
		for(Rating rating: user.getRatings()) {
			RatingDTO ratingDTO = new RatingDTO();
			BeanUtils.copyProperties(rating, ratingDTO);
			ratingDTOs.add(ratingDTO);
		}
		userDTO.setRatings(ratingDTOs);
		return userDTO;
	}

	@Override
	public List<UserDTO> findByLastName(String lastName) {
		List<UserDTO> userDTOs = new ArrayList<>();
		for(User user: userRepository.findByLastName(lastName)) {
			UserDTO userDTO = new UserDTO();
			AccountDTO accountDTO = new AccountDTO();
			List<VehicleDTO> vehicleDTOs = new ArrayList<>();
			List<RatingDTO> ratingDTOs = new ArrayList<>();
			BeanUtils.copyProperties(user, userDTO);
			BeanUtils.copyProperties(user.getAccount(), accountDTO);
			userDTO.setAccount(accountDTO);
			for(Vehicle vehicle: user.getVehicles()) {
				VehicleDTO vehicleDTO = new VehicleDTO();
				BeanUtils.copyProperties(vehicle, vehicleDTO);
				vehicleDTOs.add(vehicleDTO);
			}
			userDTO.setVehicles(vehicleDTOs);
			for(Rating rating: user.getRatings()) {
				RatingDTO ratingDTO = new RatingDTO();
				BeanUtils.copyProperties(rating, ratingDTO);
				ratingDTOs.add(ratingDTO);
			}
			userDTO.setRatings(ratingDTOs);
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public UserDTO findByAccount(AccountDTO accountDTO) {
		Account account = new Account();
		BeanUtils.copyProperties(accountDTO, account);
		User user = userRepository.findByAccount(account);
		UserDTO userDTO = new UserDTO();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		List<RatingDTO> ratingDTOs = new ArrayList<>();
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getAccount(), accountDTO);
		userDTO.setAccount(accountDTO);
		for(Vehicle vehicle: user.getVehicles()) {
			VehicleDTO vehicleDTO = new VehicleDTO();
			BeanUtils.copyProperties(vehicle, vehicleDTO);
			vehicleDTOs.add(vehicleDTO);
		}
		userDTO.setVehicles(vehicleDTOs);
		for(Rating rating: user.getRatings()) {
			RatingDTO ratingDTO = new RatingDTO();
			BeanUtils.copyProperties(rating, ratingDTO);
			ratingDTOs.add(ratingDTO);
		}
		userDTO.setRatings(ratingDTOs);
		return userDTO;
	}

}
