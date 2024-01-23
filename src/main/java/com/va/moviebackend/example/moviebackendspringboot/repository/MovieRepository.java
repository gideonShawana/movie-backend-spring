package com.va.moviebackend.example.moviebackendspringboot.repository;


import com.va.moviebackend.example.moviebackendspringboot.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie,String> {

     Mono<Movie> findMovieByImdbId(String imdbId);
}
