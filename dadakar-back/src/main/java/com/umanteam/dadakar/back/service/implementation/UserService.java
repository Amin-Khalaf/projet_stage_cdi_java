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
	
	/* copy from UserDTO to User */
	private User userDTOToUser(UserDTO userDTO) {
		User user = new User();
		Account account = new Account();
		List<Vehicle> vehicles = new ArrayList<>();
		List<Rating> ratings = new ArrayList<>();
		BeanUtils.copyProperties(userDTO, user);
		if (userDTO.getAccount() != null) {
			BeanUtils.copyProperties(userDTO.getAccount(), account);
			user.setAccount(account);
		}
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
				if (ratingDTO.getRater() != null) {
					User rater = new User();
					BeanUtils.copyProperties(ratingDTO.getRater(), rater);
					rating.setRater(rater);
				}
				ratings.add(rating);
			}
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
		if (user.getAccount() != null) {
			BeanUtils.copyProperties(user.getAccount(), accountDTO);
			userDTO.setAccount(accountDTO);
		}
		if(user.getVehicles() != null) {
			for(Vehicle vehicle: user.getVehicles()) {
				VehicleDTO vehicleDTO = new VehicleDTO();
				BeanUtils.copyProperties(vehicle, vehicleDTO);
				vehicleDTOs.add(vehicleDTO);
			}
		}
		userDTO.setVehicles(vehicleDTOs);
		if(user.getRatings() != null) {
			for(Rating rating: user.getRatings()) {
				RatingDTO ratingDTO = new RatingDTO();
				BeanUtils.copyProperties(rating, ratingDTO);
				if (rating.getRater() != null) {
					UserDTO raterDTO = new UserDTO();
					BeanUtils.copyProperties(rating.getRater(), raterDTO);
					ratingDTO.setRater(raterDTO);
				}
				ratingDTOs.add(ratingDTO);
			}
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
		List<User> users = userRepository.findAll();
		if(users != null) for(User user: users) userDTOs.add(userToUserDTO(user));
		return userDTOs;
	}

	@Override
	public UserDTO findById(String id) {
		User user = userRepository.findOne(id);
		if(user != null) return userToUserDTO(user);
		return new UserDTO();
	}

	@Override
	public List<UserDTO> findByLastName(String lastName) {
		List<UserDTO> userDTOs = new ArrayList<>();
		List<User> users = userRepository.findByLastName(lastName);
		if(users != null) for(User user: users) userDTOs.add(userToUserDTO(user));
		return userDTOs;
	}

	@Override
	public UserDTO findByAccountUsername(String username) {
		User user = userRepository.findByAccountUsername(username);
		if(user != null) return userToUserDTO(user);
		return new UserDTO();
	}

	@Override
	public int countUserRatingsLessThan(String id, int value) {
		User user = userRepository.findOne(id);
		int compte = 0;
		if(user != null) for(Rating rating: user.getRatings()) if(rating.getValue() < value) compte++;
		return compte;
	}

}
