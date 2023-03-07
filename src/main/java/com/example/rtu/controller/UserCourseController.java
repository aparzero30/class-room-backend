package com.example.rtu.controller;

import com.example.rtu.entity.User;
import com.example.rtu.entity.UserCourse;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-classroom/userCourse")
public class UserCourseController {

    @Autowired
    UserCourseService userCourseService;

    @PostMapping("/enroll")
    public ResponseEntity<UserCourse> Enroll(@RequestParam Long userId, @RequestParam Long courseId ){

        UserCourse userCourse = userCourseService.enroll(userId, courseId);

        if(userCourse==null){
            throw new EntityNotFoundException("not found "+userId);
        }
        return new ResponseEntity<>(userCourse, HttpStatus.OK);
    }
    @DeleteMapping("/unEnroll")
    public ResponseEntity<String> UnEnroll(@RequestParam Long userId, @RequestParam Long courseId ){

        String result = userCourseService.unEnroll(userId, courseId);

        if( result==null){
            throw new EntityNotFoundException("not found "+userId);
        }
        return new ResponseEntity<>( result, HttpStatus.OK);

    }

    @GetMapping("findForStudent")
    public List<UserCourse> findAllForStudent(Long userId){
       return userCourseService.findAllForStudent(userId);
    }







}
