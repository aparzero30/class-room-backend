package com.example.rtu.repository;

import com.example.rtu.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepo extends JpaRepository<Discussion, Long> {
//    List<Discussion> findAllByCourseId(Long courseId);
    List<Discussion> findByCourseCourseId(Long courseId);
}
