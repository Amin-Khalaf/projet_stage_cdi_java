package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Address;

public interface AddressRepository extends MongoRepository<Address, String> {
	List<Address> findByTown(String town);
	Address findByTownAndDistrict(String town, String district);
}
