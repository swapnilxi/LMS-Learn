package com.lms.lmsproject.LmsProject.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Teacher;

@Repository
public interface TeacherRepo extends MongoRepository<Teacher, ObjectId> {

    Optional<Teacher> findByTeacherEmail(String teacherEmail);

    Optional<Teacher> findByTeacherUsername(String teacherUsername);
}
