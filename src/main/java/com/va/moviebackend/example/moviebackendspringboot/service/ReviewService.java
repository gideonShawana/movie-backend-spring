package com.va.moviebackend.example.moviebackendspringboot.service;

import com.va.moviebackend.example.moviebackendspringboot.model.Movie;
import com.va.moviebackend.example.moviebackendspringboot.model.Review;
import com.va.moviebackend.example.moviebackendspringboot.repository.ReviewRepository;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository, ReactiveMongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<Review> createReview(String reviewBody, String movieImdbId) {
        Review review = new Review();
        review.setBody(reviewBody);

        return reviewRepository.insert(review)
                .flatMap(savedReview -> {
                    String reviewId = savedReview.get_id();

                    return mongoTemplate.updateFirst(
                            Query.query(Criteria.where("imdbId").is(movieImdbId)),
                            new Update().push("reviewIds", reviewId),
                            Movie.class
                    ).flatMap(updateResult -> {
                        // Log the updated movie after the update operation
                        return mongoTemplate.findOne(Query.query(Criteria.where("imdbId").is(movieImdbId)), Movie.class)
                                .doOnNext(updatedMovie -> {
                                    System.out.println("Updated Movie with reviewIds: " + updatedMovie);
                                })
                                .thenReturn(savedReview);
                    });
                });
    }



}
