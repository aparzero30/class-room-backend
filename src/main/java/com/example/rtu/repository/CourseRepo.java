package com.example.rtu.repository;


import com.example.rtu.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findAllByProfId(Long userId);
}
