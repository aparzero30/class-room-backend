package com.example.rtu.controller;


import com.example.rtu.entity.Course;
import com.example.rtu.entity.Discussion;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.repository.DiscussionRepo;
import com.example.rtu.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-classroom/discussion")
public class DiscussionController {

    @Autowired
    DiscussionService discussionService;

    @PostMapping("/publish")
    public ResponseEntity<Discussion> publishDiscussion(@RequestParam String title, @RequestParam  String body,  @RequestParam  Long courseId,  @RequestParam Long userId){

        Discussion discussion = discussionService.publishDiscussion(title, body, courseId, userId);

        if(discussion==null){
           throw new EntityNotFoundException("not found ");
       }
        return  new ResponseEntity<>(discussion, HttpStatus.OK);
    }
    @GetMapping("/findById")
    public ResponseEntity<Discussion> findCourseById(@RequestParam Long discussionId){
        if(discussionService.getDiscussionById(discussionId)==null){
            throw new EntityNotFoundException("not found "+discussionId);
        }
        return new ResponseEntity<>(discussionService.getDiscussionById(discussionId), HttpStatus.OK);
    }

    @PatchMapping ("/update")
    public ResponseEntity<String>  updateStudent(@RequestParam String title,@RequestParam String body,@RequestParam Long discussionId,@RequestParam Long courseId,@RequestParam Long userId){
        String result = discussionService.updateDiscussion(title,body,discussionId, courseId, userId);
        if(result==null){
            throw new EntityNotFoundException("not found "+discussionId);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String>  deleteDiscussion(@RequestParam Long discussionId, @RequestParam Long courseId,@RequestParam  Long userId){
        String result = discussionService.deleteDiscussion(discussionId, courseId, userId);
        if(result==null){
            throw new EntityNotFoundException("not found "+discussionId);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/findAll")
    public  ResponseEntity<List<Discussion>> getAllDiscussion(Long courseId){
        List<Discussion> discussions = discussionService.getAllDiscussion(courseId);
        if(courseId==null){
            throw new EntityNotFoundException("not found "+courseId);
        }
        return new ResponseEntity<>( discussions, HttpStatus.OK);
    }


}
