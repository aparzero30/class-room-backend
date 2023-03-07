package com.example.rtu.controller;

import com.example.rtu.auth.JWTService;
import com.example.rtu.entity.User;
import com.example.rtu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserExtractor {


    @Autowired
    private  JWTService jwtService;

    @Autowired
    private UserService userService;




    public  User extract(String token){

        String[] tokens = token.split(" ");
        token = tokens[1];
        String email = jwtService.extractUserName(token);
        return  userService.findByEmail(email);

    }
}
