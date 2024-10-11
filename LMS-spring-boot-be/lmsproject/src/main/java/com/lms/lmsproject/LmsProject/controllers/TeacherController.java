package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.customApiResponse.APIResponse;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.service.TeacherService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(path = "/all-teacher")
    public ResponseEntity<APIResponse<List<Teacher>>> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherService.fetchAllTeachers();
            return new ResponseEntity<>(new APIResponse<List<Teacher>>(HttpStatus.OK.value(), "Success", teachers),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new APIResponse<List<Teacher>>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", null),
                    HttpStatus.OK);
        }
    }

    @PostMapping(path = "/create-teacher")
    public ResponseEntity<APIResponse<Teacher>> createTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.createNewTeacher(teacher);
            return new ResponseEntity<>(new APIResponse<Teacher>(HttpStatus.CREATED.value(), "Success", teacher),
                    HttpStatus.OK);
        } catch (Exception IllegalArgumentException) {
            return new ResponseEntity<>(
                    new APIResponse<Teacher>(HttpStatus.CONFLICT.value(), IllegalArgumentException.getMessage(), null),
                    HttpStatus.OK);
        }
    }

    @GetMapping(path = "find-teacher/{email}")
    public ResponseEntity<APIResponse<Teacher>> findTeacher(@PathVariable String email) {
        try {
            Teacher teacher = teacherService.findByTeacherEmail(email);
            return new ResponseEntity<>(new APIResponse<Teacher>(HttpStatus.OK.value(), "Success", teacher),
                    HttpStatus.OK);
        } catch (Exception UsernameNotFoundException) {
            return new ResponseEntity<>(new APIResponse<Teacher>(HttpStatus.NOT_FOUND.value(),
                    UsernameNotFoundException.getMessage(), null), HttpStatus.OK);
        }
    }

    // @PutMapping(path = "/update-teacher")
    // public ResponseEntity<APIResponse<Teacher>> updateTeacher(@RequestBody Teacher reqteacher) {
    //     try {
    //         Teacher updatedTeacher = teacherService.updateTeacher(reqteacher);
    //         return new ResponseEntity<>(new APIResponse<Teacher>(HttpStatus.CREATED.value(), "Success", updatedTeacher),
    //                 HttpStatus.OK);
    //     } catch (Exception IllegalArgumentException) {
    //         return new ResponseEntity<>(
    //                 new APIResponse<Teacher>(HttpStatus.CONFLICT.value(), IllegalArgumentException.getMessage(), null),
    //                 HttpStatus.OK);
    //     }
    // }

    @DeleteMapping(path = "/delete-teacher/{id}")
    public ResponseEntity<APIResponse<Void>> deleteUserByUserId(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>(new APIResponse<>(HttpStatus.NO_CONTENT.value(), "Success", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new APIResponse<>(HttpStatus.NOT_FOUND.value(), "Id Not Found !", null),
                    HttpStatus.OK);
        }
    }

}