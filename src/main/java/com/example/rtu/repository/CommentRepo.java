package com.example.rtu.repository;

import com.example.rtu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByDiscussionDiscussionId(Long discussionId);

}
