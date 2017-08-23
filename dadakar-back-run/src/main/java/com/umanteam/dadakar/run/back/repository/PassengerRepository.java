package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.User;
import com.umanteam.dadakar.run.back.entities.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
	Passenger findByUser(User user);
}
