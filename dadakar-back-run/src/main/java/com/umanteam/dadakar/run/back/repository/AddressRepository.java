package com.umanteam.dadakar.run.back.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Address;

public interface AddressRepository extends MongoRepository<Address, String> {
	List<Address> findByPostCode(String postCode);
	List<Address> findByTown(String town);
	List<Address> findByPostCodeAndTown(String postCode, String Town);
	List<Address> findByPostCodeAndTownAndDistrict(String postCode, String town, String district);
}
