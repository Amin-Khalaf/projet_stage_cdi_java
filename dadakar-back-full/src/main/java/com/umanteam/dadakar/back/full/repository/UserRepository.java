package com.umanteam.dadakar.back.full.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.Account;
import com.umanteam.dadakar.back.full.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByLastName(String lastName);
	User findByAccount(Account account);
	User findByAccountUsername(String username);
}
