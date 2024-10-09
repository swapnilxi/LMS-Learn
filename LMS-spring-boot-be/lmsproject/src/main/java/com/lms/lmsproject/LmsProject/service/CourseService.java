package com.lms.lmsproject.LmsProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.entity.Course;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.repository.CourseRepo;
import com.lms.lmsproject.LmsProject.repository.TeacherRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    private Teacher cachedTeacher;

    private Teacher getAuthenticatedTeacher() {
        if (cachedTeacher == null) {
            // Get the logged-in teacher's username
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUsername();
            // Fetch the teacher from the database
            cachedTeacher = teacherRepo.findByTeacherUsername(username);
            if (cachedTeacher == null) {
                throw new RuntimeException("Teacher not found");
            }
        }
        return cachedTeacher;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course createNewCourse(Course course) {
        Course newCourse = Course.builder()
                .courseTitle(course.getCourseTitle())
                .courseDescription(course.getCourseDescription())
                .teacher(getAuthenticatedTeacher())
                .teacherName(getAuthenticatedTeacher().getTeacherUsername())
                .courseUrl(course.getCourseUrl())
                .duration(course.getDuration())
                .build();

        return courseRepo.save(newCourse);
    }

}
