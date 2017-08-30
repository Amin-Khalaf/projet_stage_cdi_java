package com.umanteam.dadakar.back;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.Rating;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.back.repository.AccountRepository;
import com.umanteam.dadakar.back.repository.RatingRepository;
import com.umanteam.dadakar.back.repository.UserRepository;
import com.umanteam.dadakar.back.repository.VehiculeRepository;
import com.umanteam.dadakar.back.service.implementation.VehicleService;


@SpringBootApplication
public class DadakarBackApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	VehiculeRepository vehicleRepository;

	@Autowired
	VehicleService vehicleService;
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackApplication.class, args);
	}

	// override run from CommandLineRunner to allow tests
	@Override
	public void run(String... arg0) throws Exception {

//		accountTest();
//		ratingTest();
//		testVehicleRepo();
//		testVehicleService();
//		userTest();
		
	}

	private void accountTest() {
		accountRepository.deleteAll();
		for (int i = 0; i < 10; i++) {
			Role role = Role.USER;
			switch (i % 3) {
			case 0:
				role = Role.USER;
				break;
			case 1:
				role = Role.ADMIN;
				break;
			case 2:
				role = Role.SUPERUSER;
				break;
			}
			Account account = new Account("username" + i, "password" + i, role);
			account = accountRepository.insert(account);
			System.out.println(account);
		}

	}

	public void testVehicleRepo() {
		System.out.println("=== test vehicle repository ===");
		// deleteAll
		System.out.println("deleteAll");
		vehicleRepository.deleteAll();
		// save1
		System.out.println("save1");
		Vehicle entity = new Vehicle("vehicule1", "Renault", "Clio", "Bleue", "photo1id", "carreg1id", "abc 1234 def",
				5);
		entity = vehicleRepository.insert(entity);
		System.out.println(entity);
		// save2
		System.out.println("save2");
		Vehicle entity2 = new Vehicle("vehicule2", "Peugeot", "404", "Rouge", "photo2id", "carreg2id", "abc 1237 def",
				6);
		entity2 = vehicleRepository.insert(entity2);
		System.out.println(entity2);
		// update
		System.out.println("update");
		entity2.setColor("Jaune");
		entity2 = vehicleRepository.save(entity2);
		System.out.println(entity2);
		// findAll
		System.out.println("findAll");
		List<Vehicle> vehicles = vehicleRepository.findAll();
		System.out.println(vehicles);
		// findOne
		System.out.println("findOne");
		String id = entity2.getVehicleId();
		entity2 = null;
		entity2 = vehicleRepository.findOne(id);
		System.out.println(entity2);
		// delete
		System.out.println("delete");
		vehicleRepository.delete(entity2);
		vehicles = vehicleRepository.findAll();
		System.out.println(vehicles);
		// deleteAll
		System.out.println("deleteAll");
		vehicleRepository.deleteAll();
	}
	
	private void ratingTest() {
		ratingRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			Rating rating = new Rating(i, new User(), "test" + i);
			rating = ratingRepository.insert(rating);
			System.out.println(rating);
		}
	}
	
	public void testVehicleService() {
		System.out.println("=== test vehicle service ===");
		// save1
		System.out.println("save1");
		VehicleDTO vehicle = new VehicleDTO("vehicule1", "Renault", "Clio", "Bleue", "photo1id", "carreg1id",
				"abc 1234 def", 5);
		vehicle = vehicleService.addOrUpdate(vehicle);
		System.out.println(vehicle);
		// save2
		System.out.println("save2");
		VehicleDTO vehicle2 = new VehicleDTO("vehicule2", "Peugeot", "404", "Rouge", "photo2id", "carreg2id",
				"abc 1237 def", 6);
		vehicle2 = vehicleService.addOrUpdate(vehicle2);
		System.out.println(vehicle2);
		// update
		System.out.println("update");
		vehicle2.setColor("Jaune");
		vehicle2 = vehicleService.addOrUpdate(vehicle2);
		System.out.println(vehicle2);
		// findAll
		System.out.println("findAll");
		List<VehicleDTO> vehicles = vehicleService.findAll();
		System.out.println(vehicles);
		// findOne
		System.out.println("findOne");
		String id = vehicle2.getVehicleId();
		vehicle2 = null;
		vehicle2 = vehicleService.findById(id);
		System.out.println(vehicle2);
		// delete
		System.out.println("delete");
		vehicleService.delete(vehicle2.getVehicleId());
		vehicles = vehicleService.findAll();
		System.out.println(vehicles);
	}

	private void userTest() {
		userRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			User user = new User(accountRepository.findByUsername("username" + i), "firstName" + i, "lastName" + i, "", "", "", "");
			List<Vehicle> vehicles = new ArrayList<>();
			List<Rating> ratings = new ArrayList<>();
			vehicles.add(vehicleRepository.save(new Vehicle("V" + i, "peugeot", "206", "rouge", "", "", "ab-123-cd", 5)));
			user.setVehicles(vehicles);
			user.setRatings(ratings);
			user = userRepository.insert(user);
			System.out.println(user);
		}
		
	}
}
