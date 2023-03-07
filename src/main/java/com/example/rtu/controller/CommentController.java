package com.example.rtu.controller;

import com.example.rtu.entity.Comment;
import com.example.rtu.entity.Course;
import com.example.rtu.exceptions.EntityNotFoundException;
import com.example.rtu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-classroom/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestParam Comment comment){
        if(commentService.addComment(comment) ==null){
            throw new EntityNotFoundException("not found "+comment.getCommentId());
        }
        return  new ResponseEntity<>(commentService.addComment(comment), HttpStatus.OK);
    }

    @GetMapping("/findComment")
    public ResponseEntity<Comment> findCommentById(@RequestParam Long commentId){
        if(commentService.getCommentById(commentId)==null){
            throw new EntityNotFoundException("not found "+commentId);
        }
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }
    @PatchMapping("/editComment")
    public ResponseEntity<String>  editComment(@RequestParam Long commentId,@RequestParam String message){
        if(commentService.updateComment(commentId, message)==null){
            throw new EntityNotFoundException("not found "+commentId);
        }
        return new ResponseEntity<>("updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment")
    public  ResponseEntity<String> deleteComment(@RequestParam Long commentId,@RequestParam Long userId){
        String result  = commentService.deleteComment(commentId, userId);
        if(result==null){
            throw new EntityNotFoundException("not found "+commentId);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/allComment")
    public ResponseEntity<List<Comment>>getAllCommentByDiscussion(@RequestParam Long commentId){

        List<Comment> comments = commentService.getAllCommentByDiscussion(commentId);
        if(comments==null){
            throw new EntityNotFoundException("not found "+commentId);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }





}
