package com.va.moviebackend.example.moviebackendspringboot.controller;


import com.va.moviebackend.example.moviebackendspringboot.model.Review;
import com.va.moviebackend.example.moviebackendspringboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Mono<ResponseEntity<Review>> createReview(@RequestBody Map<String, String> payload) {
        return reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"))
                .map(savedReview -> ResponseEntity.status(HttpStatus.CREATED).body(savedReview))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
