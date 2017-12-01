package com.umanteam.dadakar.login.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.umanteam.dadakar.login.back.entities.Account;
import com.umanteam.dadakar.login.back.enums.Role;
import com.umanteam.dadakar.login.back.repository.AccountRepository;


@SpringBootApplication
public class DadakarBackLoginApplication implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;
		
	public static void main(String[] args) {
		SpringApplication.run(DadakarBackLoginApplication.class, args);
	}

	// override run from CommandLineRunner to allow tests
	@Override
	public void run(String... arg0) throws Exception {

//		accountTest();
//		createSampleData();
	}

	public void createSampleData() {
		// empty data
		accountRepository.deleteAll();
		
		// create accounts
		Account account1 = new Account("user1", "pass1", Role.USER);
		account1 = accountRepository.save(account1);
		System.out.println(account1);
		Account account2 = new Account("user2", "pass2", Role.USER);
		account2 = accountRepository.save(account2);
		System.out.println(account2);
		Account admin1 = new Account("admin1", "pass1", Role.ADMIN);
		admin1 = accountRepository.save(admin1);
		System.out.println(admin1);
		Account super1 = new Account("super1", "pass1", Role.SUPERUSER);
		super1 = accountRepository.save(super1);
		System.out.println(admin1);

	}
	
	public void accountTest() {
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
}
