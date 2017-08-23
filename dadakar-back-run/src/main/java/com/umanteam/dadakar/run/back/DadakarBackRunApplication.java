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

import com.umanteam.dadakar.run.back.dto.WayPointDTO;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.repository.WayPointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IWayPointService;
import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.entities.Vehicle;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.RunPrice;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;
import com.umanteam.dadakar.run.back.repository.RunPriceRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		testWaypointRepository();
		testWaypointService();
		passengerTest();
		runPriceTest();
		testRunRepository();

	}

	public void testWaypointRepository() {
		System.out.println("=== test waypoint repository ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();

		// save1
		System.out.println("save1 ---");
		WayPoint entity = new WayPoint(1, LocalDate.of(2017, 7, 21), LocalTime.of(13, 15),
				java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 1", "Dakar", "20040", 3, 150);
		entity = waypointRepository.insert(entity);
		System.out.println(entity);

		// save2
		System.out.println("save2 ---");
		WayPoint entity2 = new WayPoint(1, LocalDate.of(2017, 7, 21), LocalTime.of(13, 15),
				java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 2", "Dakar", "20040", 3, 150);
		entity2 = waypointRepository.insert(entity2);
		System.out.println(entity2);

		// update
		System.out.println("update ---");
		entity2.setFlexibility(Duration.ofMinutes(60));
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
		WayPointDTO waypoint = new WayPointDTO(1, LocalDate.of(2017, 7, 21), LocalTime.of(13, 15),
				java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 1", "Dakar", "20040", 3, 150);
		waypoint = waypointService.add(waypoint);
		System.out.println(waypoint);

		// save2
		System.out.println("save2 ---");
		WayPointDTO waypoint2 = new WayPointDTO(1, LocalDate.of(2017, 7, 21), LocalTime.of(13, 15),
				java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 2", "Dakar", "20040", 3, 150);
		waypoint2 = waypointService.add(waypoint2);
		System.out.println(waypoint2);

		// update
		System.out.println("update ---");
		waypoint2.setFlexibility(Duration.ofMinutes(60));
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
			Passenger passenger = passengerRepository
					.insert(new Passenger(user, new WayPoint(), new WayPoint(), Luggage.PETIT, 35.20));
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

	private void testRunRepository() {
		runRepository.deleteAll();
		System.out.println("test RunRepository");
		Account account = new Account("username", "password", Role.USER);
		List<Vehicle> vehicles = new ArrayList<>();
		Vehicle vehicle = new Vehicle("vehicule1", "Renault", "25", "grise", null, null, "ab123cd", 6);
		vehicles.add(vehicle);
		User user = new User(account, "firstname", "lastname", "", "", "", "");
		user.setVehicles(vehicles);
		List<WayPoint> waypoints = new ArrayList<>();
		WayPoint waypoint = new WayPoint(1, LocalDate.of(2017, 7, 26), LocalTime.of(7, 30), Duration.ofMinutes(15),
				"rue de la mairie", "Dakar - district1", "Dakar", "", 3, 150);
		WayPoint waypoint1 = new WayPoint(2, LocalDate.of(2017, 7, 26), LocalTime.of(7, 45), Duration.ofMinutes(15),
				"rue de la mairie", "Dakar - district2", "Dakar", "", 3, 200);
		WayPoint waypoint2 = new WayPoint(3, LocalDate.of(2017, 7, 26), LocalTime.of(8, 00), Duration.ofMinutes(15),
				"rue de la mairie", "Dakar - district3", "Dakar", "", 3, 100);
		waypoints.add(waypoint);
		waypoints.add(waypoint1);
		waypoints.add(waypoint2);
		Run run = new Run(user, vehicle, waypoints, null, null, Luggage.PETIT);
		runRepository.insert(run);
	}
}
