package com.lms.lmsproject.LmsProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.adminEmail = :adminEmail")
    Optional<Admin> findByEmail(@Param ("adminEmail") String adminEmail);

    
}
