package com.example.rtu.repository;

import com.example.rtu.entity.User;
import com.example.rtu.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseRepo extends JpaRepository<UserCourse, Long> {
    UserCourse findByUserIdAndCourseId(Long userId, Long courseId);


    List<UserCourse> findAllByUserId(Long userId);

    List<UserCourse> findAllByCourseId(Long courseId);
}
