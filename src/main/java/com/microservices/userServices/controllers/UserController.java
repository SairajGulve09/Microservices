package com.microservices.userServices.controllers;

import com.microservices.userServices.entity.User;
import com.microservices.userServices.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {

        User newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    //single user

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getOneUser(@PathVariable String userId)
    {
        User newUser = userService.getSingleUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    //creating fallbackmethod for circuit breaker
    // return type and the parameter of the method should be same
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        System.out.println("Fallback is executed because service is down");
        User user = User.builder().email("dummyemail@gmail.com").username("Dummy").about("This dummy user is shown because service is down").userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    //all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }
}
