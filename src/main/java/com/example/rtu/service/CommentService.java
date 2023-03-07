package com.example.rtu.service;


import com.example.rtu.entity.Comment;
import com.example.rtu.entity.Discussion;
import com.example.rtu.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    @Autowired
    CommentRepo commentRepo;

    DiscussionService discussionService;

    public Comment addComment(Comment comment){
        return commentRepo.save(comment);
    }

    public Comment getCommentById(Long commentId){
        Optional<Comment> comment = commentRepo.findById(commentId);
        if(comment.isPresent()){
            return comment.get();
        }
        return null;
    }

    public String updateComment(Long commentId, String message){
        Comment comment = getCommentById(commentId);
        if(comment==null){
            return null;
        }
        comment.setMessage(message);
        commentRepo.save(comment);

        return "updated";

    }

    public String deleteComment(Long commentId, Long userId){
        Comment comment = getCommentById(commentId);
        if(comment==null){
            return null;
        }
        if(!comment.getUserId().equals(userId)){
            return null;
        }
        commentRepo.delete(comment);
        return "deleted";
    }

    public List<Comment> getAllCommentByDiscussion(Long discussionId){
        if(discussionService.getDiscussionById(discussionId)==null){
            return null;
        }
        return commentRepo.findByDiscussionDiscussionId(discussionId);
    }




}
