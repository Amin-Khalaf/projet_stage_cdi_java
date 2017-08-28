package com.umanteam.dadakar.back.full.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umanteam.dadakar.back.full.dto.AccountDTO;
import com.umanteam.dadakar.back.full.dto.RatingDTO;
import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.dto.VehicleDTO;
import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.entities.Rating;
import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.repository.UserRepository;
import com.umanteam.dadakar.back.full.service.interfaces.IUserService;

@Service("userService")
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	/* copy from User to UserDTO */
	private UserDTO userToUserDTO(User user) {
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
	public UserDTO addOrUpdate(UserDTO userDTO) {
		return userToUserDTO(userRepository.save(userDTOToUser(userDTO)));
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserDTO> userDTOs = new ArrayList<>();
		for(User user: userRepository.findAll()) userDTOs.add(userToUserDTO(user));
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
		for(User user: userRepository.findByLastName(lastName)) userDTOs.add(userToUserDTO(user));
		return userDTOs;
	}

	@Override
	public UserDTO findByAccountUsername(String username) {
		return userToUserDTO(userRepository.findByAccountUsername(username));
	}

}
