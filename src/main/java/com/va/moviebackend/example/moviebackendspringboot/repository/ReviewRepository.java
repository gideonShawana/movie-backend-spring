package com.va.moviebackend.example.moviebackendspringboot.repository;

import com.va.moviebackend.example.moviebackendspringboot.model.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review,String> {

}
