package com.umanteam.dadakar.run.back;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.run.back.dto.RunDTO;
import com.umanteam.dadakar.run.back.dto.SubRunDTO;
import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.repository.WayPointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IRunService;
import com.umanteam.dadakar.run.back.service.interfaces.IWayPointService;
import com.umanteam.dadakar.back.dto.AccountDTO;
import com.umanteam.dadakar.back.dto.UserDTO;
import com.umanteam.dadakar.back.dto.VehicleDTO;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.RunPrice;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.repository.RunPriceRepository;
import com.umanteam.dadakar.run.back.repository.SubRunRepository;
import com.umanteam.dadakar.run.back.repository.RunRepository;

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

		testWaypointRepository();
		testWaypointService();
		passengerTest();
		runPriceTest();
		subRunTest();
		
		testRunRepository();
		testRunService();

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
		waypoint = waypointService.add(waypoint);
		System.out.println(waypoint);

		// save2
		System.out.println("save2 ---");
		WayPointDTO waypoint2 = new WayPointDTO("rue de la mosquée", "Dakar - district 2", "Dakar", "20040");
		waypoint2 = waypointService.add(waypoint2);
		System.out.println(waypoint2);

		// update
		System.out.println("update ---");
		waypoint2.setDistrict("Dakar - District3");
		waypoint2 = waypointService.update(waypoint2);

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

		for (int i = 1; i < 10; i++) {
			Account account = new Account("username" + i, "password" + i, Role.USER);
			User user = new User(account, "firstName" + i, "lastName" + i, "", "", "", "");
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
			SubRun subRun = new SubRun(Duration.ofMinutes(15), waypointRepository.insert(new WayPoint(i, LocalDateTime.now(), Duration.ofMinutes(15), "notre dame", "15e", "paris", "75020", 4)), waypointRepository.insert(new WayPoint(i + 10, LocalDateTime.now().plusMinutes(35), Duration.ofMinutes(15), "la chapelle", "5e", "paris", "75020", 4)), LocalDate.now(), LocalTime.of(14, 30), LocalDate.now(), LocalTime.of(15, 05), 4, new ArrayList<Passenger>(), new ArrayList<WayPoint>(), new ArrayList<Toll>(), 22.50);
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
		//RunDTO run = new RunDTO(user, vehicle, subruns, Luggage.PETIT);
		run = runService.addRun(run);
		System.out.println(run);
		user = run.getDriver();
		
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
