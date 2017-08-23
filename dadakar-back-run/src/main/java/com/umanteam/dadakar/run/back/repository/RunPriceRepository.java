package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.RunPrice;

public interface RunPriceRepository extends MongoRepository<RunPrice, String> {
	RunPrice findByPower(int power);
}
