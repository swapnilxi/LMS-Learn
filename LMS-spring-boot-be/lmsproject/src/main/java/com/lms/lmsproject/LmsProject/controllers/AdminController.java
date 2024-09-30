package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.Admin;
import com.lms.lmsproject.LmsProject.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(path = "/all-admins")
    public List<Admin> getAllUsers() {
        return adminService.getAllAdmins();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAdminById(@PathVariable Long id){
        adminService.deleteAdmin(id);
    }

}
