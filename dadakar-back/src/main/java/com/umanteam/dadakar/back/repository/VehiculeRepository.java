package com.umanteam.dadakar.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.Vehicle;

public interface VehiculeRepository extends MongoRepository<Vehicle, String> {

}
