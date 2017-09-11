package com.umanteam.dadakar.run.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.run.back.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

}
