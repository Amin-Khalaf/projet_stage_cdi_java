package com.umanteam.dadakar.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByLastName(String lastName);
	User findByAccountUsername(String username);
}
