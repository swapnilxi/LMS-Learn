package com.lms.lmsproject.LmsProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.repository.TeacherRepo;
import com.lms.lmsproject.LmsProject.repository.UserEntRepo;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.entity.UserEnt;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntRepo userRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        UserEnt user = userRepo.findByUserName(username);
        if (user != null) {
            return buildUserDetails(user);
        }

        Teacher teacher = teacherRepo.findByTeacherUsername(username);
        if (teacher != null) {
            return buildUserDetails(teacher);
        }

        // If neither found, throw exception
        throw new UsernameNotFoundException("User not found");
    }

    private UserDetails buildUserDetails(UserEnt user) {
        String[] roles = user.getRoles().stream()
                .map(role -> role.name())
                .toArray(String[]::new);

        return User.builder()
                .username(user.getUserName())
                .password(user.getUserPassword())
                .roles(roles)
                .build();
    }

    private UserDetails buildUserDetails(Teacher teacher) {
        String[] roles = teacher.getRoles().stream()
                .map(role -> role.name())
                .toArray(String[]::new);

        return User.builder()
                .username(teacher.getTeacherUsername())
                .password(teacher.getTeacherPassword())
                .roles(roles)
                .build();
    }
}
