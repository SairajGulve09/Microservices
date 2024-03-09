package com.microservices.userServices.external.services;

import com.microservices.userServices.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    //get

    //post
    @PostMapping("/ratings")
    public ResponseEntity<Rating>  crateRating(Rating rating);
}
