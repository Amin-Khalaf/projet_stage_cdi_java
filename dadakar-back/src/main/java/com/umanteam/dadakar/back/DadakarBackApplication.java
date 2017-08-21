package com.umanteam.dadakar.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.back.entities.Account;
import com.umanteam.dadakar.back.entities.Rating;
import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.back.enums.Role;
import com.umanteam.dadakar.back.repository.AccountRepository;
import com.umanteam.dadakar.back.repository.RatingRepository;

@SpringBootApplication
public class DadakarBackApplication implements CommandLineRunner {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RatingRepository ratingRepository;

	public static void main(String[] args) {
		SpringApplication.run(DadakarBackApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		AccountTest();
		RatingTest();
		
	}
	
	private void AccountTest() {
		accountRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			Role role = Role.USER;
			switch (i%3) {
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
	
	private void RatingTest() {
		ratingRepository.deleteAll();
		for(int i = 0; i < 10; i++) {
			Rating rating = new Rating(i, new User("user" + i), "test" + i);
			rating = ratingRepository.insert(rating);
			System.out.println(rating);
		}
	}
	
}
