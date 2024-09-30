package com.lms.lmsproject.LmsProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.UserEnt;

@Repository
public interface UserEntRepo extends JpaRepository<UserEnt, Long> {

    UserEnt findByUserEmail(String userEmail);
    UserEnt findByUserName(String userName);
    
}
