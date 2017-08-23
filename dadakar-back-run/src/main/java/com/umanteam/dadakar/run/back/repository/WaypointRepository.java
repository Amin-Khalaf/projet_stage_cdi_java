package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Waypoint;

public interface WaypointRepository extends MongoRepository<Waypoint, String> {
	
	public List<Waypoint> findByDistrict(String district);
	public List<Waypoint> findByTown(String town);
	public List<Waypoint> findByPostcode(String postcode);

}
