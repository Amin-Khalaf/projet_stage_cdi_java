package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.User;

public interface UserRepository extends MongoRepository<User, String> {	
	List<User> findByLastName(String lastName);
	User findByAccountId(String accountId);
}
