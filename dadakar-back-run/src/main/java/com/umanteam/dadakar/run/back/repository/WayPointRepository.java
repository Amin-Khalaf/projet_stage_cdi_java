package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.WayPoint;

public interface WayPointRepository extends MongoRepository<WayPoint, String> {
	
}
