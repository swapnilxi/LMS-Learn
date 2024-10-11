package com.lms.lmsproject.LmsProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findByAdminEmail(String adminEmail);
    Optional<Admin> findByAdminName(String adminName);

}
