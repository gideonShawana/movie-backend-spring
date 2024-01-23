package com.va.moviebackend.example.moviebackendspringboot.controller;

import com.va.moviebackend.example.moviebackendspringboot.model.Movie;
import com.va.moviebackend.example.moviebackendspringboot.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movie")
@CrossOrigin("http://localhost:3000/")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    @PostMapping
//    public Mono<Movie> save(@RequestBody Movie movie) {
//        return movieService.save(movie);
//    }

    @GetMapping
    public Flux<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{imdbId}")
    public Mono<ResponseEntity<Movie>> getMovieByImdbId(@PathVariable String imdbId) {
        return movieService.getMovieByImdbId(imdbId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
