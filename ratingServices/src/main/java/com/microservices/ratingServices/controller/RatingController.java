package com.microservices.ratingServices.controller;

import com.microservices.ratingServices.entity.Rating;
import com.microservices.ratingServices.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create
//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));

    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getAll()
    {
        return ResponseEntity.ok(ratingService.getAllRatings());    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllByUserId(@PathVariable String userId)
    {
        return ResponseEntity.ok(ratingService.getAllByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllByHotelId(@PathVariable String hotelId)
    {
        return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
    }
}
