package com.umanteam.dadakar.back.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.umanteam.dadakar.back.entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

}
