package com.microservices.hotelServices.controller;

import com.microservices.hotelServices.entities.Hotel;
import com.microservices.hotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServices hotelServices;
    //crete
//    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.create(hotel));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        return ResponseEntity.ok(hotelServices.getAllHotels());
    }

    //get one
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(hotelServices.getSingleHotel(hotelId));
    }
}
