package com.lms.lmsproject.LmsProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.repository.UserEntRepo;
import com.lms.lmsproject.LmsProject.entity.UserEnt;

// @NoArgsConstructor
// @AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserEntRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        UserEnt user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create UserDetails object with username, password, and roles
        return User.builder()
                .username(user.getUserName())
                .password(user.getUserPassword())
                .roles(user.getRoles().toArray(new String[0])) // Spring expects roles in the form "ROLE_USER"
                .build();
    }

}
