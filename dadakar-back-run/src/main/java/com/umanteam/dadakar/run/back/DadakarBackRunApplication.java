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

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {
	
	@Autowired
	RunRepository runRepo;
	
	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		createRun();
	}
	
	@SuppressWarnings("unused")
	private void createRun() {
		List<Passenger> passengers = new ArrayList<>();
		List<SubRun> subRuns = new ArrayList<>();
		List<Toll> tolls = new ArrayList<>();
		List<WayPoint> waypoints = new ArrayList<>();
		waypoints.add(new WayPoint("gare de lyon", new Address("10 ème", "Paris")));
		User user = userRepo.findByAccountId("59bfb5277f394325d43cf723");
		SubRun subRun = new SubRun(Duration.ofMinutes(15), new WayPoint("gare de lyon", new Address("10 ème", "Paris")), new WayPoint("gare de Lille", new Address("Centre", "Lille")), LocalDate.now(), LocalTime.of(16, 20, 00), LocalDate.now(), LocalTime.of(19, 45, 00), (Integer) 4, passengers, waypoints, tolls,(Double) 8.50);
		subRuns.add(subRun);
		Run run = new Run(user,"1", subRuns, Luggage.MOYEN);
		runRepo.save(run);
		
	}
	
}
