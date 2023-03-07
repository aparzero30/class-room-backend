package com.example.rtu.entity;


import jakarta.persistence.*;

@Entity
@Table(name="comment")
public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long commentId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "discussion_id", referencedColumnName = "discussion_id")
//    private Discussion discussion;
//
//    private Long userId;
//    private String message;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussion_id", referencedColumnName = "discussion_id")
    private Discussion discussion;






    private Long userId;
    private String message;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public void setDiscussion(Discussion discussion) {
        this.discussion = discussion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
