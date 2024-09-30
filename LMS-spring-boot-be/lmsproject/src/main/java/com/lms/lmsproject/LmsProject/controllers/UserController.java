package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.UserEnt;
import com.lms.lmsproject.LmsProject.service.UserEntService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserEntService userEntService;

    @GetMapping(path = "/all-users")
    public List<UserEnt> getAllUsers() {
        return userEntService.getAllUsers();
    }

    @GetMapping(path = "/find-user/{email}")
    public UserEnt findUserByEmail(@PathVariable String email) {
        return userEntService.findUserByEmail(email);
    }

    @PutMapping(path = "/update-user")
    public void updateUserDetails(@RequestBody UserEnt user){}


    @DeleteMapping(path = "/delete/{id}")
    public void deleteUserByUserId(@PathVariable Long id) {
        userEntService.deleteUser(id);
    }

}
