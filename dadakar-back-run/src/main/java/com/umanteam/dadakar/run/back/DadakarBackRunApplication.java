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

import com.umanteam.dadakar.run.back.entities.Address;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.Run;
import com.umanteam.dadakar.run.back.entities.SubRun;
import com.umanteam.dadakar.run.back.entities.Toll;
import com.umanteam.dadakar.run.back.entities.User;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.repository.RunRepository;
import com.umanteam.dadakar.run.back.repository.UserRepository;
import com.umanteam.dadakar.run.back.repository.AddressRepository;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {
	
	@Autowired
	RunRepository runRepo;
	
	@Autowired
	UserRepository userRepo;

	@Autowired
	AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		createRun();
		populateAddresses();
	}
	
	@SuppressWarnings("unused")
	private void createRun() {
		runRepo.deleteAll();
		List<Passenger> passengers = new ArrayList<>();
		List<SubRun> subRuns = new ArrayList<>();
		List<Toll> tolls = new ArrayList<>();
		List<WayPoint> waypoints = new ArrayList<>();
		User user = userRepo.findByAccountId("59bfb5277f394325d43cf723");
		if (user != null) {
			// create pour Franck
			waypoints.add(new WayPoint("gare de lyon", new Address("10 ème", "Paris")));
			SubRun subRun = new SubRun(Duration.ofMinutes(15), new WayPoint("gare de lyon", new Address("10 ème", "Paris")), new WayPoint("gare de Lille", new Address("Centre", "Lille")), LocalDate.now(), LocalTime.of(16, 20, 00), LocalDate.now(), LocalTime.of(19, 45, 00), (Integer) 4, passengers, waypoints, tolls,(Double) 8.50);
			subRuns.add(subRun);
			Run run = new Run(user,"1", subRuns, Luggage.MOYEN);
			runRepo.save(run);
		} else {
			user = userRepo.findOne("59c0d5f11c30f1c864b09227");
			if (user != null) {
				// create pour Raphaël
				waypoints.add(new WayPoint("rue de la gare", addressRepository.findByTownAndDistrict("Dakar", "Biscuiterie")));
				System.out.println(user);
				SubRun subRun = new SubRun(Duration.ofMinutes(15), new WayPoint("rue de la gare", addressRepository.findByTownAndDistrict("Dakar", "Biscuiterie")), new WayPoint("rue de la mairie", addressRepository.findByTownAndDistrict("Pikine", "Nord")), LocalDate.now(), LocalTime.of(16, 20, 00), LocalDate.now(), LocalTime.of(19, 45, 00), (Integer) 4, passengers, waypoints, tolls,(Double) 8.50);
				subRuns.add(subRun);
				Run run = new Run(user,"1", subRuns, Luggage.MOYEN);
				runRepo.save(run);
			}
		}
	}
		
	private void populateAddresses(){
		addressRepository.deleteAll();
		Address address = new Address("Biscuiterie","Dakar");
		addressRepository.insert(address);
		address = new Address("Cambérène","Dakar");
		addressRepository.insert(address);
		address = new Address("Dieuppeul-Derklé","Dakar");
		addressRepository.insert(address);
		address = new Address("Fann-Point E-Amitié","Dakar");
		addressRepository.insert(address);
		address = new Address("Gueule Tapée-Fass-Colobane","Dakar");
		addressRepository.insert(address);
		address = new Address("Gorée","Dakar");
		addressRepository.insert(address);
		address = new Address("Grand Yoff","Dakar");
		addressRepository.insert(address);
		address = new Address("Grand Dakar","Dakar");
		addressRepository.insert(address);
		address = new Address("Hann Bel-Air","Dakar");
		addressRepository.insert(address);
		address = new Address("HLM","Dakar");
		addressRepository.insert(address);
		address = new Address("Médina","Dakar");
		addressRepository.insert(address);
		address = new Address("Mermoz-Sacré-Cœur","Dakar");
		addressRepository.insert(address);
		address = new Address("Ngor","Dakar");
		addressRepository.insert(address);
		address = new Address("Ouakam","Dakar");
		addressRepository.insert(address);
		address = new Address("Parcelles Assainies","Dakar");
		addressRepository.insert(address);
		address = new Address("Patte d'Oie","Dakar");
		addressRepository.insert(address);
		address = new Address("Dakar-Plateau","Dakar");
		addressRepository.insert(address);
		address = new Address("Sicap-Liberté","Dakar");
		addressRepository.insert(address);
		address = new Address("Yoff","Dakar");
		addressRepository.insert(address);
		address = new Address("Est","Pikine");
		addressRepository.insert(address);
		address = new Address("Nord","Pikine");
		addressRepository.insert(address);
		address = new Address("Ouest","Pikine");
		addressRepository.insert(address);
		address = new Address("Nord","Thies");
		addressRepository.insert(address);
		address = new Address("Sud","Thies");
		addressRepository.insert(address);
		address = new Address("Diassap","Diassap");
		addressRepository.insert(address);
	}
	
}
