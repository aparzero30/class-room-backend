package com.example.rtu.controller;


import com.example.rtu.auth.AuthenticationRequest;
import com.example.rtu.auth.AuthenticationResponse;
import com.example.rtu.auth.AuthenticationService;
import com.example.rtu.auth.JWTService;
;
import com.example.rtu.entity.Course;
import com.example.rtu.entity.Role;
import com.example.rtu.entity.User;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.service.CourseService;
import com.example.rtu.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/e-classroom/course")
@CrossOrigin("http://localhost:4200")


public class CourseController {


    @Autowired
    CourseService courseService;

    @Autowired
    JWTService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserExtractor userExtractor;


    @GetMapping("/demo")
    public String Demo() {
        return "hello";
    }


    @GetMapping("/forInstructors")
    public ResponseEntity<List<Course>> findForInstructor(@RequestHeader("Authorization") String token) {
        User user = userExtractor.extract(token);
        return new ResponseEntity<>(courseService.getAllCoursesForInstructors(user.getUserId()), HttpStatus.OK);
    }


    @PostMapping("/createCourse")
    public ResponseEntity<Course> createCourse(@RequestHeader("Authorization") String token, @RequestParam String courseName){
        User user = userExtractor.extract(token);
        System.out.println(user.getRole());
        return new ResponseEntity<>(courseService.createCourse(courseName,user.getUserId()), HttpStatus.OK);
    }














}

