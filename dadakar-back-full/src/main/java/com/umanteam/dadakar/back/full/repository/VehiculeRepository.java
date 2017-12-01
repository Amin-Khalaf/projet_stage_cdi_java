package com.umanteam.dadakar.back.full.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.Vehicle;

public interface VehiculeRepository extends MongoRepository<Vehicle, String> {

}
