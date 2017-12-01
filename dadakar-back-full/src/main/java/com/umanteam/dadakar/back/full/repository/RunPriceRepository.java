package com.umanteam.dadakar.back.full.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.RunPrice;

public interface RunPriceRepository extends MongoRepository<RunPrice, String> {
	RunPrice findByPower(int power);
}
