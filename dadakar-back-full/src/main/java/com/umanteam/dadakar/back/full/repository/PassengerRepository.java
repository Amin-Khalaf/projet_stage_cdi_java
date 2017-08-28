package com.umanteam.dadakar.back.full.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
	List<Passenger> findByUserUserId(String id);
}
