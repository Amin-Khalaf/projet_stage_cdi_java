package com.umanteam.dadakar.back.full;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.entities.Passenger;
import com.umanteam.dadakar.back.full.entities.Run;
import com.umanteam.dadakar.back.full.entities.SubRun;
import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.entities.WayPoint;
import com.umanteam.dadakar.back.full.enums.Luggage;
import com.umanteam.dadakar.back.full.enums.Role;
import com.umanteam.dadakar.back.full.repository.AccountRepository;
import com.umanteam.dadakar.back.full.repository.PassengerRepository;
import com.umanteam.dadakar.back.full.repository.RatingRepository;
import com.umanteam.dadakar.back.full.repository.RunRepository;
import com.umanteam.dadakar.back.full.repository.SubRunRepository;
import com.umanteam.dadakar.back.full.repository.UserRepository;
import com.umanteam.dadakar.back.full.repository.VehiculeRepository;
import com.umanteam.dadakar.back.full.repository.WayPointRepository;

@SpringBootApplication
public class DadakarBackFullApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	PassengerRepository passengerRepo;

	@Autowired
	RatingRepository ratingRepo;

	@Autowired
	RunRepository runRepo;

	@Autowired
	SubRunRepository subrunRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	VehiculeRepository vehicleRepo;

	@Autowired
	WayPointRepository waypointRepo;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackFullApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createSampleData();
	}

	private void createSampleData() {
		// empty data
		accountRepo.deleteAll();
		passengerRepo.deleteAll();
		ratingRepo.deleteAll();
		runRepo.deleteAll();
		subrunRepo.deleteAll();
		userRepo.deleteAll();
		vehicleRepo.deleteAll();
		waypointRepo.deleteAll();

		// create accounts
		Account account1 = new Account("user1", "pass1", Role.USER);
		account1 = accountRepo.save(account1);
		System.out.println(account1);
		Account account2 = new Account("user2", "pass2", Role.USER);
		account2 = accountRepo.save(account2);
		System.out.println(account2);
		Account admin1 = new Account("admin1", "pass1", Role.ADMIN);
		admin1 = accountRepo.save(admin1);
		System.out.println(admin1);

		// create users
		User user1 = new User(account1, "firstName1", "lastName1", "mail1", "idCard1", "photo1", "drivingLicence1");
		user1 = userRepo.save(user1);
		System.out.println(user1);
		User user2 = new User(account2, "firstName2", "lastName2", "mail2", "idCard2", "photo2", "drivingLicence2");
		user2 = userRepo.save(user2);
		System.out.println(user2);

		// create vehicle
		Vehicle vehicle1 = new Vehicle("voiture1", "Renault", "Megane", "Noire", "", "", "ab123cd", 6);
		vehicle1 = vehicleRepo.save(vehicle1);
		System.out.println(vehicle1);
		Vehicle vehicle2 = new Vehicle("voiture2", "Renault", "Megane", "Noire", "", "", "ab123cd", 6);
		vehicle2 = vehicleRepo.save(vehicle2);
		System.out.println(vehicle2);

		// update user1 with vehicle1
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(vehicle1);
		user1.setVehicles(vehicles);
		user1 = userRepo.save(user1);
		System.out.println(user1);
		// update user2 with vehicle2
		List<Vehicle> vehicles2 = new ArrayList<>();
		vehicles.add(vehicle2);
		user2.setVehicles(vehicles2);
		user2 = userRepo.save(user2);
		System.out.println(user2);

		// create run with user1
		// create waypoints
		WayPoint waypoint1 = new WayPoint("rue de la mairie", "Dakar - district1", "Dakar", "");
		waypoint1 = waypointRepo.save(waypoint1);
		System.out.println(waypoint1);
		WayPoint waypoint2 = new WayPoint("rue de la mosquée", "Dakar - district2", "Dakar", "");
		waypoint2 = waypointRepo.save(waypoint2);
		System.out.println(waypoint2);
		// create subruns
		List<WayPoint> startingPoints = new ArrayList<>();
		startingPoints.add(waypoint1);
		SubRun subrun1 = new SubRun(Duration.ofMinutes(15), waypoint1, waypoint2, LocalDate.of(2017, 8, 31),
				LocalTime.of(8, 15), LocalDate.of(2017, 8, 31), LocalTime.of(8, 50), 3, null, startingPoints, null,
				200.00);
		subrun1 = subrunRepo.save(subrun1);
		System.out.println(subrun1);
		// save run
		List<SubRun> subruns = new ArrayList<>();
		subruns.add(subrun1);
		Run run1 = new Run(user1, vehicle1, subruns, Luggage.PETIT);
		run1 = runRepo.insert(run1);
		System.out.println(run1);

		// update run1 with passenger user2
		// create passenger
		Passenger passenger1 = new Passenger(user2, Luggage.PETIT, 200.00);
		passenger1 = passengerRepo.save(passenger1);
		System.out.println(passenger1);
		// add passenger to subrun
		List<Passenger> passengers = new ArrayList<>();
		passengers.add(passenger1);
		subrun1.setPassengers(passengers);
		subrun1 = subrunRepo.save(subrun1);
		System.out.println(subrun1);
		// update run
		run1 = runRepo.save(run1);
		System.out.println(run1);

		// create run2 with user2
		// create waypoints
		WayPoint waypoint3 = new WayPoint("rue de la mairie", "Dakar - district1", "Dakar", "");
		waypoint3 = waypointRepo.save(waypoint3);
		System.out.println(waypoint3);
		WayPoint waypoint4 = new WayPoint("rue de la mosquée", "Dakar - district2", "Dakar", "");
		waypoint4 = waypointRepo.save(waypoint4);
		System.out.println(waypoint4);
		// create subruns
		List<WayPoint> startingPoints2 = new ArrayList<>();
		startingPoints.add(waypoint3);
		SubRun subrun2 = new SubRun(Duration.ofMinutes(15), waypoint3, waypoint4, LocalDate.of(2017, 8, 1),
				LocalTime.of(8, 15), LocalDate.of(2017, 8, 1), LocalTime.of(8, 50), 3, null, startingPoints2, null,
				200.00);
		subrun2 = subrunRepo.save(subrun2);
		System.out.println(subrun2);
		// save run
		List<SubRun> subruns2 = new ArrayList<>();
		subruns2.add(subrun2);
		Run run2 = new Run(user2, vehicle2, subruns2, Luggage.MOYEN);
		run2 = runRepo.insert(run2);
		System.out.println(run1);
		
		// test searches
		System.out.println("---account by id--");
		// account
		Account testAccount = accountRepo.findOne(account1.getAccountId());
		System.out.println(testAccount);

		System.out.println("---all accounts --");
		for (Account account : accountRepo.findAll()) {
			System.out.println(account);
		}

		System.out.println("---account by user name--");
		testAccount = accountRepo.findByUsername(user1.getLastName());

		System.out.println("---account by role--");
		System.out.println(testAccount);
		for (Account account : accountRepo.findByRole(Role.USER)) {
			System.out.println(account);
		}

		// KO
//		System.out.println("--- account by role1 and role2--");
//		for (Account account : accountRepo.findByRoleIsAndRoleIs(Role.SUPERUSER, Role.ADMIN)) {
//			System.out.println(account);
//		}

		System.out.println("---account by banned false--");
		for (Account account : accountRepo.findByBanned(false)) {
			System.out.println(account);
		}

		System.out.println("---account by deleted false--");
		for (Account account : accountRepo.findByDeleted(false)) {
			System.out.println(account);
		}

		System.out.println("---account by deleted false and role--");
		for (Account account : accountRepo.findByDeletedAndRole(false, Role.USER)) {
			System.out.println(account);
		}

		System.out.println("---user by id--");
		User userTest = userRepo.findOne(user1.getUserId());
		System.out.println(userTest);

		System.out.println("---all users--");
		for (User user : userRepo.findAll()) {
			System.out.println(user);
		}

		System.out.println("---users by lastname--");
		for (User user : userRepo.findByLastName(user1.getLastName())) {
			System.out.println(user);
		}

		System.out.println("---user by account--");
		userTest = userRepo.findByAccount(account1);
		System.out.println(userTest);

		System.out.println("---user by account username--");
		userTest = userRepo.findByAccountUsername(account1.getUsername());
		System.out.println(userTest);

		System.out.println("---vehicle by id--");
		System.out.println(vehicleRepo.findOne(vehicle1.getVehicleId()));

		System.out.println("---all vehicles--");
		for (Vehicle vehicle : vehicleRepo.findAll()) {
			System.out.println(vehicle);
		}

		System.out.println("---run by id--");
		System.out.println(runRepo.findOne(run1.getRunId()));

		System.out.println("---all runs--");
		for (Run run : runRepo.findAll()) {
			System.out.println(run);
		}

		System.out.println("---run by driver--");
		for (Run run : runRepo.findByDriver(user1)) {
			System.out.println(run);
		}

		// KO 
//		System.out.println("---run by driver user id--");
//		for (Run run : runRepo.findByDriverUserId(user1.getUserId())) {
//			System.out.println(run);
//		}

		System.out.println("---run by driver last name--");
		for (Run run : runRepo.findByDriverLastName(user1.getLastName())) {
			System.out.println(run);
		}

		System.out.println("---run by passenger user--");
		for (Run run : runRepo.findBySubRunsPassengersUser(user2)) {
			System.out.println(run);
		}

		System.out.println("---run by user (driver or passenger user) --");
		for (Run run : runRepo.findByDriverOrSubRunsPassengersUser(user2, user2)) {
			System.out.println(run);
		}

		System.out.println("---find runs by address and date--");
		for (Run run : runRepo
				.findBySubRunsStartingPointsDistrictAndSubRunsStartingPointsTownAndSubRunsStartDateAndSubRunsEndPlaceDistrictAndSubRunsEndPlaceTownAndSubRunsAvailableSeatsGreaterThan(
						"Dakar - district1", "Dakar", LocalDate.of(2017, 8, 31), "Dakar - district2", "Dakar", 0)) {
			System.out.println(run);
		}

	}

}
