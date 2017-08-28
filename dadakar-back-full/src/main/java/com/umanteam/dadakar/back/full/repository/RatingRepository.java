package com.umanteam.dadakar.back.full.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.full.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

}
