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

import com.umanteam.dadakar.back.full.dto.AccountDTO;
import com.umanteam.dadakar.back.full.dto.RunDTO;
import com.umanteam.dadakar.back.full.dto.SubRunDTO;
import com.umanteam.dadakar.back.full.dto.UserDTO;
import com.umanteam.dadakar.back.full.dto.VehicleDTO;
import com.umanteam.dadakar.back.full.dto.WayPointDTO;
import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.entities.Passenger;
import com.umanteam.dadakar.back.full.entities.Rating;
import com.umanteam.dadakar.back.full.entities.Run;
import com.umanteam.dadakar.back.full.entities.RunPrice;
import com.umanteam.dadakar.back.full.entities.SubRun;
import com.umanteam.dadakar.back.full.entities.Toll;
import com.umanteam.dadakar.back.full.entities.User;
import com.umanteam.dadakar.back.full.entities.Vehicle;
import com.umanteam.dadakar.back.full.entities.WayPoint;
import com.umanteam.dadakar.back.full.enums.Luggage;
import com.umanteam.dadakar.back.full.enums.Role;
import com.umanteam.dadakar.back.full.repository.AccountRepository;
import com.umanteam.dadakar.back.full.repository.PassengerRepository;
import com.umanteam.dadakar.back.full.repository.RatingRepository;
import com.umanteam.dadakar.back.full.repository.RunPriceRepository;
import com.umanteam.dadakar.back.full.repository.RunRepository;
import com.umanteam.dadakar.back.full.repository.SubRunRepository;
import com.umanteam.dadakar.back.full.repository.UserRepository;
import com.umanteam.dadakar.back.full.repository.VehiculeRepository;
import com.umanteam.dadakar.back.full.repository.WayPointRepository;
import com.umanteam.dadakar.back.full.service.implementation.VehicleService;
import com.umanteam.dadakar.back.full.service.interfaces.IRunService;
import com.umanteam.dadakar.back.full.service.interfaces.IWayPointService;

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

	@Autowired
	private WayPointRepository waypointRepository;

	@Autowired
	private IWayPointService waypointService;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private RunPriceRepository runPriceRepository;

	@Autowired
	private RunRepository runRepository;
	
	@Autowired
	private SubRunRepository subRunRepository;
	
	@Autowired
	private IRunService runService;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackFullApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createSampleData();

		accountTest();
		ratingTest();
		testVehicleRepo();
		testVehicleService();
		userTest();
		
		testWaypointRepository();
		testWaypointService();
		passengerTest();
		runPriceTest();
		subRunTest();
		
		testRunRepository();
		testRunService();

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

	public void testWaypointRepository() {
		System.out.println("=== test waypoint repository ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();

		// save1
		System.out.println("save1 ---");
		WayPoint entity = new WayPoint("rue du chemin", "Dakar - district 1", "Daker", "20040");
		entity = waypointRepository.insert(entity);
		System.out.println(entity);

		// save2
		System.out.println("save2 ---");
		WayPoint entity2 = new WayPoint("rue du chemin", "Dakar - district 1", "Daker", "20040");
		entity2 = waypointRepository.insert(entity2);
		System.out.println(entity2);

		// update
		System.out.println("update ---");
		entity2.setDistrict("Dakar - district2");
		entity2 = waypointRepository.save(entity2);

		// findAll
		System.out.println("find all ---");
		List<WayPoint> waypoints = waypointRepository.findAll();
		System.out.println(waypoints);

		// findByID
		System.out.println("find by id ---");
		String id = entity2.getId();
		entity2 = null;
		entity2 = waypointRepository.findOne(id);
		System.out.println(entity2);

		// find by District
		System.out.println("find by district ----");
		waypoints = waypointRepository.findByDistrict("Dakar - district 1");
		System.out.println(waypoints);

		// find by Town
		System.out.println("find by town ---");
		waypoints = waypointRepository.findByTown("Dakar");
		System.out.println(waypoints);

		// find by postcode
		System.out.println("find by postcode ---");
		waypoints = waypointRepository.findByPostcode("20040");
		System.out.println(waypoints);

		// delete
		System.out.println("delete ----");
		waypointRepository.delete(id);
		waypoints = waypointRepository.findAll();
		System.out.println(waypoints);

	}

	public void testWaypointService() {
		System.out.println("=== test waypoint service ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();

		// save1
		System.out.println("save1 ---");
		WayPointDTO waypoint = new WayPointDTO("rue de la mosquée", "Dakar - district 1", "Dakar", "20040");
		waypoint = waypointService.addOrUpdate(waypoint);
		System.out.println(waypoint);

		// save2
		System.out.println("save2 ---");
		WayPointDTO waypoint2 = new WayPointDTO("rue de la mosquée", "Dakar - district 2", "Dakar", "20040");
		waypoint2 = waypointService.addOrUpdate(waypoint2);
		System.out.println(waypoint2);

		// update
		System.out.println("update ---");
		waypoint2.setDistrict("Dakar - District3");
		waypoint2 = waypointService.addOrUpdate(waypoint2);

		// findAll
		System.out.println("find all ---");
		List<WayPointDTO> waypoints = waypointService.findAll();
		System.out.println(waypoints);

		// findByID
		System.out.println("find by id ---");
		String id = waypoint2.getId();
		waypoint2 = null;
		waypoint2 = waypointService.findById(id);
		System.out.println(waypoint2);

		// find by District
		System.out.println("find by district ----");
		waypoints = waypointService.findByDistrict("Dakar - district 1");
		System.out.println(waypoints);

		// find by Town
		System.out.println("find by town ---");
		waypoints = waypointService.findByTown("Dakar");
		System.out.println(waypoints);

		// find by postcode
		System.out.println("find by postcode ---");
		waypoints = waypointService.findByPostcode("20040");
		System.out.println(waypoints);

		// delete
		System.out.println("delete ----");
		waypointService.delete(id);
		waypoints = waypointService.findAll();
		System.out.println(waypoints);

	}

	private void passengerTest() {
		passengerRepository.deleteAll();
		for (int i = 1; i < 10; i++) {
			User user = userRepository.save(new User(accountRepository.findByUsername("username" + 1), "firstName" + i * 2, "lastName" + i * 2, "", "", "", ""));
			user.setVehicles(new ArrayList<>());
			user.setRatings(new ArrayList<>());
			Passenger passenger = passengerRepository.insert(new Passenger(user, Luggage.PETIT, 35.20));
			System.out.println(passenger);
		}

	}

	private void runPriceTest() {
		runPriceRepository.deleteAll();
		for (int i = 0; i < 10; i++) {
			RunPrice runPrice = new RunPrice(i, i * 3, i * 5, i * 2, 0.35);
			runPrice = runPriceRepository.insert(runPrice);
			System.out.println(runPrice);
		}
	}
	
	private void subRunTest() {
		subRunRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			SubRun subRun = new SubRun(Duration.ofMinutes(15), waypointRepository.insert(new WayPoint("notre dame", "15e", "paris", "75020")), waypointRepository.insert(new WayPoint("la chapelle", "5e", "paris", "75020")), LocalDate.now(), LocalTime.of(14, 30), LocalDate.now(), LocalTime.of(15, 05), 4, new ArrayList<Passenger>(), new ArrayList<WayPoint>(), new ArrayList<Toll>(), 22.50);
			subRun = subRunRepository.insert(subRun);
			System.out.println(subRun);
		}
	}
	

	private void testRunRepository() {
		runRepository.deleteAll();
		System.out.println("=== test RunRepository ===");
		System.out.println("--- Add run ---");
		Account account = new Account("username", "password", Role.USER);
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle("vehicule1", "Renault", "25", "grise", null, null, "ab123cd", 6);
		vehicles.add(vehicle);
		User user = new User(account, "firstname", "lastname", "", "", "", "");
		user.setVehicles(vehicles);
/*		List<WayPoint> waypoints = new ArrayList<>();
		WayPoint waypoint = new WayPoint("rue de la mairie", "Dakar - district1", "Dakar", "");
		WayPoint waypoint1 = new WayPoint("rue de la mairie", "Dakar - district2", "Dakar", "");
		WayPoint waypoint2 = new WayPoint("rue de la mairie", "Dakar - district3", "Dakar", "");
		waypoints.add(waypoint);
		waypoints.add(waypoint1);
		waypoints.add(waypoint2);
*/		
		List<SubRun> subruns = new ArrayList<>();
		SubRun subrun = new SubRun();
		subruns.add(subrun);
		Run run = new Run(user, vehicle, subruns, Luggage.PETIT);
		run = runRepository.insert(run);
		System.out.println(run);
		
		System.out.println("--- save 2 ---");
		Run run2 = new Run(user, vehicle, subruns, Luggage.MOYEN);
		run2 = runRepository.insert(run2);
		System.out.println(run2);
		
		String id = run2.getRunId();
		
		System.out.println("--- update run ---");
		run2.setLuggageType(Luggage.GRAND);
		run2 = runRepository.save(run2);
		System.out.println(run2);
		
		System.out.println("--- find all ---");
		List<Run> runs = runRepository.findAll();
		for(Run var : runs)
			System.out.println(var);
		
		System.out.println("--- findbyid ---");
		run2 = null;
		run2 = runRepository.findOne(id);
		System.out.println(run2);
		
		System.out.println("--- findByDriver ---");
		runs = null;
		runs = runRepository.findByDriver(user);
		for(Run var : runs)
			System.out.println(var);
		
		System.out.println("--- delete ---");
		runRepository.delete(id);
		runs = runRepository.findAll();
		for(Run var : runs)
			System.out.println(var);
	}
	
	private void testRunService(){
		runRepository.deleteAll();
		
		System.out.println("=== test run service ===");
		System.out.println("--- Add run ---");
		AccountDTO account = new AccountDTO("username", "password", Role.USER);
		List<VehicleDTO> vehicles = new ArrayList<>();
		VehicleDTO vehicle = new VehicleDTO("vehicule1", "Renault", "25", "grise", null, null, "ab123cd", 6);
		vehicles.add(vehicle);
		UserDTO user = new UserDTO(account, "firstname", "lastname", "", "", "", "");
		user.setVehicles(vehicles);
		
		// other infos to be managed
		
		List<SubRunDTO> subruns = new ArrayList<>();
		SubRunDTO subrun = new SubRunDTO();
		subruns.add(subrun);
		RunDTO run = new RunDTO(user, vehicle, subruns, Luggage.PETIT);
		run = runService.addRun(run);
		System.out.println(run);
//		user = run.getDriver();
		
		System.out.println("--- save 2 ---");
		RunDTO run2 = new RunDTO(user, vehicle, subruns, Luggage.MOYEN);
		run2 = runService.addRun(run2);
		System.out.println(run2);
		
		String id = run2.getRunId();
		
		System.out.println("--- update run ---");
		run2.setLuggageType(Luggage.GRAND);
		run2 = runService.updateRun(run2);
		System.out.println(run2);
		
		System.out.println("--- find all ---");
		List<RunDTO> runs = runService.findAllRuns();
		for(RunDTO var : runs)
			System.out.println(var);
		
		System.out.println("--- findbyid ---");
		run2 = null;
		run2 = runService.findRunsById(id);
		System.out.println(run2);
		
		System.out.println("--- findByDriver ---");
		runs = null;
		runs = runService.findRunsByDriver(user);
		for(RunDTO var : runs)
			System.out.println(var);
		
		System.out.println("--- delete ---");
		runService.deleteRun(id);;
		runs = runService.findAllRuns();
		for(RunDTO var : runs)
			System.out.println(var);
	}
	
}
