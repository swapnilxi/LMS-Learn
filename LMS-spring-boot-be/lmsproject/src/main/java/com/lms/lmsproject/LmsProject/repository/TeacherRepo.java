package com.lms.lmsproject.LmsProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Teacher findByTeacherEmail(String teacherEmail);

    Teacher findByTeacherUsername(String teacherUsername);
}
