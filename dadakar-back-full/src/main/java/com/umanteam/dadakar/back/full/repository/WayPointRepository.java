package com.umanteam.dadakar.back.full.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.WayPoint;

public interface WayPointRepository extends MongoRepository<WayPoint, String> {
	
	public List<WayPoint> findByDistrict(String district);
	public List<WayPoint> findByTown(String town);
	public List<WayPoint> findByPostcode(String postcode);

}
