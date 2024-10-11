package com.lms.lmsproject.LmsProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByTeacherEmail(String teacherEmail);

    Optional<Teacher> findByTeacherUsername(String teacherUsername);
}
