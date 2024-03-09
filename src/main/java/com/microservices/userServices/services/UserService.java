package com.microservices.userServices.services;

import com.microservices.userServices.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    // create user
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get single user
    User getSingleUser(String userId);
}
