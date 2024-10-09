package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.Course;
import com.lms.lmsproject.LmsProject.service.CourseService;


@RestController
@RequestMapping(path = "/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @GetMapping(path = "/all-course")
    public List<Course> fetchAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping(path = "/create-course")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course newCourse = courseService.createNewCourse(course);
        return new ResponseEntity<>(newCourse,HttpStatus.OK);
    }

}
