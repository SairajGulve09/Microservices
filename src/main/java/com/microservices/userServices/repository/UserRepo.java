package com.microservices.userServices.repository;

import com.microservices.userServices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {

}
