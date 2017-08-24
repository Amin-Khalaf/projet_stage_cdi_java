package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.SubRun;

public interface SubRunRepository extends MongoRepository<SubRun, String> {

}
