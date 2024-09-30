package com.lms.lmsproject.LmsProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.lmsproject.LmsProject.entity.Trainer;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Long> {
    
}
