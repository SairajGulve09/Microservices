package com.microservices.hotelServices.implementation;

import com.microservices.hotelServices.Repository.HotelRepository;
import com.microservices.hotelServices.entities.Hotel;
import com.microservices.hotelServices.exception.ResourceNotFoundException;
import com.microservices.hotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        Hotel newHotel = hotelRepository.save(hotel);
        return newHotel;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel id not fount in server" + id));
//        return null;
    }
}
