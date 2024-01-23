package com.va.moviebackend.example.moviebackendspringboot.service;

import com.va.moviebackend.example.moviebackendspringboot.model.Movie;
import com.va.moviebackend.example.moviebackendspringboot.repository.MovieRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Save a movie to the database
//    public Mono<Movie> save(Movie movie) {
//        return movieRepository.save(movie);
//    }

    // Retrieve all movies from the database
    public Flux<Movie> getAll() {
        return movieRepository.findAll();
    }

    // Retrieve a movie by its IMDb ID
    public Mono<Movie> getMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }


}
