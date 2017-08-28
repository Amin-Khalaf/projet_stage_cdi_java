package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
	List<Passenger> findByUserId(String userId);
}
