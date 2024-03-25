package com.microservices.ratingServices.services;

import com.microservices.ratingServices.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {
    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getAllRatings();

    //get all by userid
    List<Rating> getAllByUserId(String userId);

    //get all by hotel
    List<Rating> getAllByHotelId(String hotelId);
}
