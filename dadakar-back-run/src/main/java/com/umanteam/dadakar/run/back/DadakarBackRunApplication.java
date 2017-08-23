package com.umanteam.dadakar.run.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.run.back.entities.Passenger;
import com.umanteam.dadakar.run.back.entities.WayPoint;
import com.umanteam.dadakar.run.back.enums.Luggage;
import com.umanteam.dadakar.run.back.repository.PassengerRepository;

@SpringBootApplication
public class DadakarBackRunApplication implements CommandLineRunner {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackRunApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		passengerTest();
		
	}
	
	private void passengerTest() {
		
		for(int i = 1; i < 10; i++) {
			Account account = new Account("username" + i, "password" + i, Role.USER);
			User user = new User(account, "firstName" + i, "lastName" + i, "", "", "", "");
			Passenger passenger = passengerRepository.insert(new Passenger(user, new WayPoint("1234" + i), new WayPoint("5678" + i), Luggage.PETIT, 35.20));
			System.out.println(passenger);
		}
		
	}
}
