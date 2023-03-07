package com.example.rtu.service;


import com.example.rtu.entity.User;
import com.example.rtu.entity.UserCourse;
import com.example.rtu.repository.UserCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService{


    @Autowired
    UserCourseRepo userCourseRepo;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    public UserCourse enroll(Long userId, Long courseId){

        if(userService.findUserById(userId)==null || courseService.findCourseById(courseId)==null){
            return null;
        }
        UserCourse userCourse = new UserCourse();
        userCourse.setUserId(userId);
        userCourse.setCourseId(courseId);
        return userCourseRepo.save(userCourse);

    }

    public String unEnroll(Long userId, Long courseId){

        if(userService.findUserById(userId)==null|| courseService.findCourseById(courseId)==null){
            return null;
        }

        UserCourse userCourse = userCourseRepo.findByUserIdAndCourseId(userId, courseId);

        if(userCourseRepo.findByUserIdAndCourseId(userId, courseId)==null){
            return null;
        }

        userCourseRepo.delete(userCourse);
        return "un-enrolled";

    }

    public List<UserCourse> findAllForStudent(Long userId){
        return userCourseRepo.findAllByUserId(userId);
    }

    public List<UserCourse> findAllStudents(Long courseId){
        return userCourseRepo.findAllByCourseId(courseId);
    }









}
