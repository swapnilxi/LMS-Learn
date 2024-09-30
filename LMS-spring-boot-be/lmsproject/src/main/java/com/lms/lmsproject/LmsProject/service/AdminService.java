package com.lms.lmsproject.LmsProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.entity.Admin;
import com.lms.lmsproject.LmsProject.repository.AdminRepo;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepoService;

    public List<Admin> getAllAdmins(){
        return adminRepoService.findAll();
    }

    public void deleteAdmin(Long id){
        adminRepoService.deleteById(id);
    }

}
