package com.umanteam.dadakar.run.back;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.run.back.dto.WaypointDTO;
import com.umanteam.dadakar.run.back.entities.Waypoint;
import com.umanteam.dadakar.run.back.repository.WaypointRepository;
import com.umanteam.dadakar.run.back.service.interfaces.IWaypointService;

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {

	@Autowired
	WaypointRepository waypointRepository;
	
	@Autowired
	IWaypointService waypointService;
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
//		testWaypointRepository();
//		testWaypointService();
	}
	
	public void testWaypointRepository(){
		System.out.println("=== test waypoint repository ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();
		
		// save1
		System.out.println("save1 ---");
		Waypoint entity = new Waypoint(1, LocalDateTime.of(2017, 7, 21, 13, 15), java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 1", "Dakar", "20040", 3);
		entity = waypointRepository.insert(entity);
		System.out.println(entity);
		
		// save2
		System.out.println("save2 ---");
		Waypoint entity2 = new Waypoint(1, LocalDateTime.of(2017, 7, 21, 13, 15), java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 2", "Dakar", "20040", 3);
		entity2 = waypointRepository.insert(entity2);
		System.out.println(entity2);
		
		// update
		System.out.println("update ---");
		entity2.setFlexibility(Duration.ofMinutes(60));
		entity2 = waypointRepository.save(entity2);

		// findAll
		System.out.println("find all ---");
		List<Waypoint> waypoints = waypointRepository.findAll();
		System.out.println(waypoints);
		
		// findByID
		System.out.println("find by id ---");
		String id = entity2.getId();
		entity2 = null;
		entity2 = waypointRepository.findOne(id);
		System.out.println(entity2);
		
		//find by District
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
	
	public void testWaypointService(){
		System.out.println("=== test waypoint service ===");
		// deleteAll
		System.out.println("deleteAll ---");
		waypointRepository.deleteAll();
		
		// save1
		System.out.println("save1 ---");
		WaypointDTO waypoint = new WaypointDTO(1, LocalDateTime.of(2017, 7, 21, 13, 15), java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 1", "Dakar", "20040", 3);
		waypoint = waypointService.add(waypoint);
		System.out.println(waypoint);
		
		// save2
		System.out.println("save2 ---");
		WaypointDTO waypoint2 = new WaypointDTO(1, LocalDateTime.of(2017, 7, 21, 13, 15), java.time.Duration.ofMinutes(15), "rue de la mosquée", "Dakar - district 2", "Dakar", "20040", 3);
		waypoint2 = waypointService.add(waypoint2);
		System.out.println(waypoint2);
		
		// update
		System.out.println("update ---");
		waypoint2.setFlexibility(Duration.ofMinutes(60));
		waypoint2 = waypointService.update(waypoint2);

		// findAll
		System.out.println("find all ---");
		List<WaypointDTO> waypoints = waypointService.findAll();
		System.out.println(waypoints);
		
		// findByID
		System.out.println("find by id ---");
		String id = waypoint2.getId();
		waypoint2 = null;
		waypoint2 = waypointService.findById(id);
		System.out.println(waypoint2);
		
		//find by District
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
}
