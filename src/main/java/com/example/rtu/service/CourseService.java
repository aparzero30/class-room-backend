package com.example.rtu.service;


import com.example.rtu.entity.Course;
import com.example.rtu.entity.User;
import com.example.rtu.repository.CourseRepo;
import com.example.rtu.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepo courseRepo;


    @Autowired
    private UserService userService = new UserService();

   public Course createCourse(String courseName, Long userId){

       User user = userService.findUserById(userId);



       if(user==null){
           return null;
       }else{
                Course course = new Course();
                course.setCourseName(courseName);
                course.setProfId(userId);


                return courseRepo.save(course);
       }
   }

    public String deleteCourseById(Long courseId, Long userId){

        User user = userService.findUserById(userId);

        if(user==null|| !user.getRole().equals("instructor")){
            return null;
        }else{
            Course course = findCourseById(courseId);
            if(course==null){
                return  null;
            }else{
                courseRepo.delete(course);
            }
            return "deleted";
        }

    }

    public Course findCourseById(Long courseId){
        Optional<Course> course = courseRepo.findById(courseId);
        if(course.isPresent()){

            return course.get();
        }
        return  null;
    }

    public String updateCourse(String courseName, Long courseId){
        Course course = findCourseById(courseId);
        if(course==null){
            return null;
        }
        else{
            course.setCourseName(courseName);
            courseRepo.save(course);
        }
        return "updated";
    }

    public List<Course> getAllCourses(){
        return  courseRepo.findAll();
    }

    public List<Course> getAllCoursesForInstructors(Long userId){

       return  courseRepo.findAllByProfId(userId);
    }









}
