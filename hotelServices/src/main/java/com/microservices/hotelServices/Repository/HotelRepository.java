package com.microservices.hotelServices.Repository;

import com.microservices.hotelServices.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    
}
