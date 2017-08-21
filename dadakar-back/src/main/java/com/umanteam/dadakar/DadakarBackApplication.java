package com.umanteam.dadakar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.back.DTO.VehicleDTO;
import com.umanteam.dadakar.back.entity.Vehicle;
import com.umanteam.dadakar.back.repository.VehiculeRepository;
import com.umanteam.dadakar.back.service.implementation.VehicleService;

@SpringBootApplication
public class DadakarBackApplication implements CommandLineRunner {

	@Autowired
	VehiculeRepository vehicleRepository;
	
	@Autowired
	VehicleService vehicleService;
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackApplication.class, args);
	}
	
	// override run from CommandLineRunner to allow tests
	@Override
	public void run(String... args) throws Exception {

		testVehicleRepo();
		testVehicleService();
	}
	
	public void testVehicleRepo(){
		System.out.println("=== test vehicle repository ===");
		// deleteAll
		System.out.println("deleteAll");
		vehicleRepository.deleteAll();
		// save1
		System.out.println("save1");
		Vehicle entity = new Vehicle("vehicule1", "Renault", "Clio", "Bleue", "photo1id", "carreg1id", "abc 1234 def", 5);
		entity = vehicleRepository.insert(entity);
		System.out.println(entity);
		// save2
		System.out.println("save2");
		Vehicle entity2 = new Vehicle("vehicule2", "Peugeot", "404", "Rouge", "photo2id", "carreg2id", "abc 1237 def", 6);
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
		//findOne
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
	
	public void testVehicleService(){
		System.out.println("=== test vehicle service ===");
		// save1
		System.out.println("save1");
		VehicleDTO vehicle = new VehicleDTO("vehicule1", "Renault", "Clio", "Bleue", "photo1id", "carreg1id", "abc 1234 def", 5);
		vehicle = vehicleService.add(vehicle);
		System.out.println(vehicle);
		// save2
		System.out.println("save2");
		VehicleDTO vehicle2 = new VehicleDTO("vehicule2", "Peugeot", "404", "Rouge", "photo2id", "carreg2id", "abc 1237 def", 6);
		vehicle2 = vehicleService.add(vehicle2);
		System.out.println(vehicle2);
		// update
		System.out.println("update");
		vehicle2.setColor("Jaune");
		vehicle2 = vehicleService.update(vehicle2);
		System.out.println(vehicle2);
		// findAll
		System.out.println("findAll");
		List<VehicleDTO> vehicles = vehicleService.findAll();
		System.out.println(vehicles);
		//findOne
		System.out.println("findOne");
		String id = vehicle2.getVehicleId();
		vehicle2 = null;
		vehicle2 = vehicleService.findOne(id);
		System.out.println(vehicle2);
		// delete
		System.out.println("delete");
		vehicleService.delete(vehicle2);
		vehicles = vehicleService.findAll();
		System.out.println(vehicles);
	}
}
