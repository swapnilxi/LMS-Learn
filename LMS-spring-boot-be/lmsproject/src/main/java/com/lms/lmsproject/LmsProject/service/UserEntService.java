package com.lms.lmsproject.LmsProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.entity.UserEnt;
import com.lms.lmsproject.LmsProject.repository.UserEntRepo;

@Service
public class UserEntService {
    
    @Autowired
    private UserEntRepo userEntRepo;

    public List<UserEnt> getAllUsers(){
        return userEntRepo.findAll();
    }

    public UserEnt findUserByEmail(String email){
        return userEntRepo.findByUserEmail(email);
    }

    public void updateUser(UserEnt user){
        
    }

    public void deleteUser(Long id){
        userEntRepo.deleteById(id);
    }
}
