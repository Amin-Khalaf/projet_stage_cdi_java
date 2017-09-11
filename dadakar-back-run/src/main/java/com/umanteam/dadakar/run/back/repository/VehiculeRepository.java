package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Vehicle;

public interface VehiculeRepository extends MongoRepository<Vehicle, String> {

}
