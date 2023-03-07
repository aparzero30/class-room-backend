package com.example.rtu.service;


import com.example.rtu.entity.Course;
import com.example.rtu.entity.Discussion;
import com.example.rtu.repository.DiscussionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {

    @Autowired
    DiscussionRepo discussionRepo;


    @Autowired
    CourseService courseService;

    public Discussion publishDiscussion(String title, String body, Long courseId, Long userId){

       Course course = courseService.findCourseById(courseId);

       if(course == null || !course.getProfId().equals(userId)){
           return null;
       }
        Discussion discussion = new Discussion();
        discussion.setTitle(title);
        discussion.setBody(body);
        discussion.setCourse(course);
        return  discussionRepo.save(discussion);

    }

    public Discussion getDiscussionById(Long discussionId){
        Optional<Discussion> discussion = discussionRepo.findById(discussionId);
        if(discussion.isPresent()){

            return discussion.get();
        }
        return  null;
    }

    public String updateDiscussion(String title, String body, Long discussionId, Long courseId, Long userId){
        Discussion discussion = getDiscussionById(discussionId);
        if(discussion==null){
            return null;
        }
        Course course = courseService.findCourseById(courseId);
        if(course==null || !course.getProfId().equals(userId) ){
            return null;
        }
        discussion.setTitle(title);
        discussion.setBody(body);
        discussionRepo.save(discussion);
        return "updated";
    }

    public String deleteDiscussion(Long discussionId, Long courseId, Long userId){
        Discussion discussion = getDiscussionById(discussionId);
        if(discussion==null){
            return null;
        }
        Course course = courseService.findCourseById(courseId);
        if(course==null || !course.getCourseId().equals(userId)){
            return null;
        }
        discussionRepo.delete(discussion);
        return "deleted";
    }

    public List<Discussion> getAllDiscussion(Long courseId){
        Course course = courseService.findCourseById(courseId);
        if(course==null){
            return null;
        }
        return discussionRepo.findByCourseCourseId(courseId);

    }





}
