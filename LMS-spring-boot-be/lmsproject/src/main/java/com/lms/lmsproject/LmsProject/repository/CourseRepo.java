package com.lms.lmsproject.LmsProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    
}
