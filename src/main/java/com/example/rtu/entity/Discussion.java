package com.example.rtu.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="discussion")
public class Discussion {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="discussion_id")
    private Long discussionId;

    private String title;
    private String body;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @JsonIgnoreProperties("discussions")
    private Course course;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    public void setDiscussionId(Long discussionId) {
        this.discussionId = discussionId;
    }

    public Long getDiscussionId() {
        return discussionId;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
