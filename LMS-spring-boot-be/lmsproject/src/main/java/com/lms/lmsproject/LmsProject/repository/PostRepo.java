package com.lms.lmsproject.LmsProject.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lmsproject.LmsProject.entity.Post;
public interface PostRepo extends JpaRepository<Post, Long> {

    
}
