package com.example.rtu.entity;


import jakarta.persistence.*;

@Entity
@Table(name="user_course")
public class UserCourse {
    @Id
    @GeneratedValue
    @Column(name = "user_course_id")
    private Long userCourseId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "course_id")
    private Long courseId;

    public Long getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
