package com.microservices.userServices.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "USERID")
    String userId;

    @Column(name = "USERNAME")
    String username;

    @Column(name = "EMAIL")
    String email;

    @Column(name = "ABOUT")
    String about;

    @Transient   //mhanje databse madhe store nai karnar ignore karel
    private List<Rating> ratings = new ArrayList<>();


}
