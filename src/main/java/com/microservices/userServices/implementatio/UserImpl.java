package com.microservices.userServices.implementatio;

import com.microservices.userServices.entity.Hotel;
import com.microservices.userServices.entity.Rating;
import com.microservices.userServices.entity.User;
import com.microservices.userServices.exception.ResourceNotFoundException;
import com.microservices.userServices.external.services.HotelService;
import com.microservices.userServices.repository.UserRepo;
import com.microservices.userServices.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getSingleUser(String userId) {
        User user =  userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id not fount in server" + userId));
        //fetch rating of the above user from RATING SERVICE
        //http://localhost:9092/ratings/users/d79485c6-c35e-4bcf-a54e-21541798a05b
        Rating[] ratingsOfUsers =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUsers);

        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:9091/hotels/e5663219-4900-4b88-a408-af5caa7bbb63

//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code {} ", forEntity.getStatusCode());

            //set hotel to rating
            rating.setHotel(hotel);

            //return the rating
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
