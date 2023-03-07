package com.example.rtu.controller;


import com.example.rtu.entity.User;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-classroom/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/findById")
    public ResponseEntity<User> findUserById(@RequestParam Long userId){
        if(userService.findUserById(userId)==null){
            throw new EntityNotFoundException("not found "+userId);

        }
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String>  deleteUserById(@RequestParam Long userId){
        if(userService.deleteUserById(userId)==null){
            throw new EntityNotFoundException("not found "+userId);
        }
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @PatchMapping ("/update")
    public ResponseEntity<String>  updateStudent(@RequestParam  Long userId, @RequestParam String name, @RequestParam String email,@RequestParam  String password,@RequestParam  String role){

       String result=userService.updateUser(userId,name,email,password,role);

        if(result.equals(null)){
            throw new EntityNotFoundException("not found "+userId);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }





}
