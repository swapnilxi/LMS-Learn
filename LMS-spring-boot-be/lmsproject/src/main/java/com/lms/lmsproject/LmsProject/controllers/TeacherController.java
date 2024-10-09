package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.service.TeacherService;

@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(path = "/allteacher")
    public List<Teacher> getAllTeachers() {
        return teacherService.fetchAllTeachers();
    }

}
