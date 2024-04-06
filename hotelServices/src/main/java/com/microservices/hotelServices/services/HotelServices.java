package com.microservices.hotelServices.services;

import com.microservices.hotelServices.entities.Hotel;

import java.util.List;

public interface HotelServices {

    //create

    Hotel create(Hotel hotel);


    //get all
    List<Hotel> getAllHotels();

    //get single
    Hotel getSingleHotel(String id);
}
