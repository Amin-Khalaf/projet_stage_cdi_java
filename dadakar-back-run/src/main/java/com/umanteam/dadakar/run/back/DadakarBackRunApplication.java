package com.umanteam.dadakar.run.back;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.Address;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.RunPrice;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.enums.ResState;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.repository.RunPriceRepository;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.repository.SubRunRepository;
import com.umanteam.dadakar.run.back.repository.WayPointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;
import com.umanteam.dadakar.run.back.service.interfaces.IWayPointService;

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {
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
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

//		testWaypointRepository();
//		testWaypointService();
//		passengerTest();
//		runPriceTest();
//		subRunTest();
		
//		testRunRepository();
//		testRunService();

	}

	public void testWaypointRepository() {
		System.out.println("=== test waypoint repository ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();

		// save1
		System.out.println("save1 ---");
		WayPoint entity = new WayPoint("rue du chemin");
		entity = waypointRepository.insert(entity);
		System.out.println(entity);

		// save2
		System.out.println("save2 ---");
		WayPoint entity2 = new WayPoint("rue du chemin");
		entity2 = waypointRepository.insert(entity2);
		System.out.println(entity2);

		// update
		System.out.println("update ---");
//		entity2.setDistrict("Dakar - district2");
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

//		// find by District
//		System.out.println("find by district ----");
//		waypoints = waypointRepository.findByDistrict("Dakar - district 1");
//		System.out.println(waypoints);
//
//		// find by Town
//		System.out.println("find by town ---");
//		waypoints = waypointRepository.findByTown("Dakar");
//		System.out.println(waypoints);
//
//		// find by postcode
//		System.out.println("find by postcode ---");
//		waypoints = waypointRepository.findByPostcode("20040");
//		System.out.println(waypoints);

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
		WayPointDTO waypoint = new WayPointDTO("rue de la mosquée");
		waypoint = waypointService.addOrUpdate(waypoint);
		System.out.println(waypoint);

		// save2
		System.out.println("save2 ---");
		WayPointDTO waypoint2 = new WayPointDTO("rue de la mosquée");
		waypoint2 = waypointService.addOrUpdate(waypoint2);
		System.out.println(waypoint2);

		// update
		System.out.println("update ---");
//		waypoint2.setDistrict("Dakar - District3");
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
//		System.out.println("find by district ----");
//		waypoints = waypointService.findByDistrict("Dakar - district 1");
//		System.out.println(waypoints);
//
//		// find by Town
//		System.out.println("find by town ---");
//		waypoints = waypointService.findByTown("Dakar");
//		System.out.println(waypoints);
//
//		// find by postcode
//		System.out.println("find by postcode ---");
//		waypoints = waypointService.findByPostcode("20040");
//		System.out.println(waypoints);

		// delete
		System.out.println("delete ----");
		waypointService.delete(id);
		waypoints = waypointService.findAll();
		System.out.println(waypoints);

	}

	public void passengerTest() {
		passengerRepository.deleteAll();
		for (int i = 1; i < 10; i++) {
			Passenger passenger = passengerRepository.insert(new Passenger("user", Luggage.PETIT, 35.20));
			System.out.println(passenger);
		}

	}

	public void runPriceTest() {
		runPriceRepository.deleteAll();
		for (int i = 0; i < 10; i++) {
			RunPrice runPrice = new RunPrice(i, i * 3, i * 5, i * 2, 0.35);
			runPrice = runPriceRepository.insert(runPrice);
			System.out.println(runPrice);
		}
	}
	
	public void subRunTest() {
		subRunRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			SubRun subRun = new SubRun(Duration.ofMinutes(15), waypointRepository.insert(new WayPoint("notre dame", new Address("15e", "paris", "75020"))), waypointRepository.insert(new WayPoint("la chapelle", new Address("5e", "paris", "75020"))), LocalDate.now(), LocalTime.of(14, 30), LocalDate.now(), LocalTime.of(15, 05), 4, new ArrayList<Passenger>(), new ArrayList<WayPoint>(), new ArrayList<Toll>(), 22.50);
			subRun = subRunRepository.insert(subRun);
			System.out.println(subRun);
		}
	}
	

	public void testRunRepository() {
		//runRepository.deleteAll();
		System.out.println("=== test RunRepository ===");
//		// get users and vehicles
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/dadakar/users", List.class);
//		List<UserDTO> usersDTO = response.getBody().;
//		UserDTO userDTO = usersDTO.get(0);
//		User user1 = new User();
//		Vehicle vehicle = new Vehicle();
//		BeanUtils.copyProperties(userDTO, user1);
//		if (userDTO.getAccount() != null) {
//			Account account = new Account();
//			BeanUtils.copyProperties(userDTO.getAccount(), account);
//			user1.setAccount(account);
//		}
//		if (userDTO.getVehicles() != null) {
//			List<Vehicle> vehicles = new ArrayList<>();
//			for (VehicleDTO vehicleDTO : userDTO.getVehicles()){
//				Vehicle var = new Vehicle();
//				BeanUtils.copyProperties(vehicleDTO, var);
//				vehicles.add(var);
//			}
//			user1.setVehicles(vehicles);
//			vehicle = vehicles.get(0);
//		}
//		User user2 = new User();
//		userDTO = usersDTO.get(1);
//		BeanUtils.copyProperties(userDTO, user2);
//		if (userDTO.getAccount() != null) {
//			Account account = new Account();
//			BeanUtils.copyProperties(userDTO.getAccount(), account);
//			user2.setAccount(account);
//		}
//		if (userDTO.getVehicles() != null) {
//			List<Vehicle> vehicles = new ArrayList<>();
//			for (VehicleDTO vehicleDTO : userDTO.getVehicles()){
//				Vehicle var = new Vehicle();
//				BeanUtils.copyProperties(vehicleDTO, var);
//				vehicles.add(var);
//			}
//			user2.setVehicles(vehicles);
//		}
//		BeanUtils.copyProperties(usersDTO.get(1), user2);
//		System.out.println("--- Add run ---");
//		List<Vehicle> vehicles = new ArrayList<>();
//		vehicles.add(vehicle);
//		// create waypoints
//		WayPoint waypoint0 = new WayPoint("rue de la mairie", "Dakar - district1", "Dakar", "");
//		WayPoint waypoint1 = new WayPoint("rue de la mairie", "Dakar - district2", "Dakar", "");
//		WayPoint waypoint2 = new WayPoint("rue de la mairie", "Dakar - district3", "Dakar", "");
//		List<WayPoint> waypoints1 = new ArrayList<>();
//		waypoints1.add(waypoint1);
//		List<WayPoint> waypoints2 = new ArrayList<>();
//		waypoints2.add(waypoint2);
//		List<WayPoint> waypoints3 = new ArrayList<>();
//		waypoints3.add(waypoint1);
//		waypoints3.add(waypoint2);
//		// create passengers
//		List<Passenger> passengers = new ArrayList<>();
//		Passenger passenger = new Passenger(user2.getUserId(), Luggage.PETIT, 200.0);
//		passengers.add(passenger);
//		// Create subruns
//		SubRun subrun0 = new SubRun(Duration.ofMinutes(15), waypoint0, waypoint1, LocalDate.of(2017, 8, 31), LocalTime.of(12, 30), 
//				LocalDate.of(2017, 8, 31), LocalTime.of(18, 30), 2, passengers, waypoints1, null, 200.0);
//		SubRun subrun1 = new SubRun(Duration.ofMinutes(15), waypoint1, waypoint2, LocalDate.of(2017, 8, 31), LocalTime.of(12, 30), 
//				LocalDate.of(2017, 8, 31), LocalTime.of(18, 30), 3, null, waypoints2, null, 200.0);
//		SubRun subrun2 = new SubRun(Duration.ofMinutes(15), waypoint0, waypoint2, LocalDate.of(2017, 8, 31), LocalTime.of(12, 30), 
//				LocalDate.of(2017, 8, 31), LocalTime.of(18, 30), 2, null, waypoints3, null, 400.0);
//		List<SubRun> subruns = new ArrayList<>();
//		subruns.add(subrun0);
//		subruns.add(subrun1);
//		subruns.add(subrun2);
//		Run run = new Run(user1.getUserId(), vehicle, subruns, Luggage.PETIT);
//		run = runRepository.insert(run);
//		System.out.println(run);
//		
//		System.out.println("--- save 2 ---");
//		Run run2 = new Run(user2.getUserId(), vehicle, subruns, Luggage.MOYEN);
//		run2 = runRepository.insert(run2);
//		System.out.println(run2);
//		
//		String id = run2.getRunId();
//		
//		System.out.println("--- update run ---");
//		run2.setLuggageType(Luggage.GRAND);
//		run2 = runRepository.save(run2);
//		System.out.println(run2);
//		
		System.out.println("--- find all ---");
		List<Run> runs = runRepository.findAll();
		for(Run var : runs)
			System.out.println(var);
//		
//		System.out.println("--- findbyid ---");
//		run2 = null;
//		run2 = runRepository.findOne(id);
//		System.out.println(run2);
//		
		System.out.println("--- findByDriver ---");
		runs = null;
		runs = runRepository.findByDriverId("59a42acf68dfb111b40deb19");
		for(Run var : runs)
			System.out.println(var);

		// find run not cancelled by driver
		System.out.println("--- find not canceled By Driver ---");
		for (Run var : runRepository.findByDriverIdAndCanceled("59a42acf68dfb111b40deb19", false)){
			System.out.println(var);
		}
		// find all run by passenger userId
		System.out.println("--- find By passenger ---");
		for (Run var : runRepository.findBySubRunsPassengersUserId("59a42acf68dfb111b40deb1b")){
			System.out.println(var);
		}

		// find run not cancelled by passenger userId
		System.out.println("--- find not canceled By passenger ---");
		List<ResState> resStates = new ArrayList<>();
		resStates.add(ResState.CANCELLED);
		for (Run var : runRepository.findBySubRunsPassengersUserIdAndCanceledAndSubRunsPassengersReservationStateNotIn("59a42acf68dfb111b40deb1b", false, resStates)){
			System.out.println(var);
		}

		// find all run by user (as driver or as passenger)
		System.out.println("--- find By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserId("59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b")){
			System.out.println(var);
		}

		// find run not cancelled by user (as driver or as passenger)
		System.out.println("--- find not canceled By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserIdAndCanceledAndSubRunsPassengersReservationStateNotIn
				("59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b", false, resStates)){
			System.out.println(var);
		}

		// find current run by user (as driver or as passenger)
		System.out.println("--- find current By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
				"59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b", LocalDate.now(), LocalTime.now())){
			System.out.println(var);
		}

		// find current run not cancelled by user (as driver or as passenger)
		System.out.println("--- find current not canceled By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserIdAndCanceledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateGreaterThanEqualAndSubRunsEstimatedEndTimeGreaterThan(
				"59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b", false, resStates, LocalDate.now(), LocalTime.now())){
			System.out.println(var);
		}

		// find passed run by user (as driver or as passenger)
		System.out.println("--- find passed By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserIdAndSubRunsEstimatedEndDateLessThan(
				"59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b", LocalDate.of(2017, 9, 1))){
			System.out.println(var);
		}

		// find passed run not cancelled by user (as driver or as passenger)
		System.out.println("--- find passed not canceled By user ---");
		for (Run var : runRepository.findByDriverIdOrSubRunsPassengersUserIdAndCanceledAndSubRunsPassengersReservationStateNotInAndSubRunsEstimatedEndDateLessThan(
				"59a42acf68dfb111b40deb1b", "59a42acf68dfb111b40deb1b", false, resStates, LocalDate.of(2017, 9, 1))){
			System.out.println(var);
		}

		//	method to find runs with subrun that match the request start point and date and the end point and run not cancelled and subrun available seat gt 0
		System.out.println("--- find By date and address ---");
		for (Run var : runRepository.findBySubRunsStartingPointsAddressDistrictAndSubRunsStartingPointsAddressTownAndSubRunsStartDateAndSubRunsEndPlaceAddressDistrictAndSubRunsEndPlaceAddressTownAndSubRunsAvailableSeatsGreaterThanAndCanceled(
				"Dakar - district1", "Dakar", LocalDate.of(2017, 8, 31), "Dakar - district2", "Dakar", 0, false)){
			System.out.println(var);
		}

	}
	
	public void testRunService(){
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
		RunDTO run = new RunDTO("user", vehicle, subruns, Luggage.PETIT);
		run = runService.addRun(run);
		System.out.println(run);
//		user = run.getDriver();
		
		System.out.println("--- save 2 ---");
		RunDTO run2 = new RunDTO("user", vehicle, subruns, Luggage.MOYEN);
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
		runs = runService.findRunsByDriverId("user");
		for(RunDTO var : runs)
			System.out.println(var);
		
		System.out.println("--- delete ---");
		runService.deleteRun(id);;
		runs = runService.findAllRuns();
		for(RunDTO var : runs)
			System.out.println(var);
	}
	
}
