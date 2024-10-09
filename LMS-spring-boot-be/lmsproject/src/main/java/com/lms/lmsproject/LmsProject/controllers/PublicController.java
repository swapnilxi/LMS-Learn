package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.Role;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.entity.UserEnt;
import com.lms.lmsproject.LmsProject.repository.UserEntRepo;
import com.lms.lmsproject.LmsProject.service.TeacherService;
import com.lms.lmsproject.LmsProject.service.UserDetailsServiceImpl;
import com.lms.lmsproject.LmsProject.service.UserEntService;
import com.lms.lmsproject.LmsProject.utils.JwtUtils;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    private UserEntRepo userEntRepo;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserEntService userEntService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping(path = "/create-user")
    public void createNewUser(@RequestBody UserEnt user) {
        user.setRoles(Set.of(Role.USER));
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userEntRepo.save(user);
    }

    @PostMapping("/login-user")
    public String loginUser(@RequestBody UserEnt user) {
        try {

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUserName());
            String jwt = jwtUtils.generateToken(userDetails);
            return jwt;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Incorret username or pass");
        }
    }

    @DeleteMapping(path = "user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userEntRepo.deleteById(id);
    }

    @GetMapping("/all")
    public List<UserEnt> getAllUsers() {
        return userEntService.getAllUsers();
    }

    // Teacher Section

    @PostMapping(path = "/create-teacher")
    public void createNewTeacher(@RequestBody Teacher teacher) {
        teacherService.createNewTeacher(teacher);
    }

    @GetMapping(path = "/allteacher")
    public List<Teacher> getAllTeachers() {
        return teacherService.fetchAllTeachers();
    }

    @PostMapping(path = "/login-teacher")
    public String loginTeacher(@RequestBody Teacher teacher) {
        try {

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(teacher.getTeacherUsername(),
                            teacher.getTeacherPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(teacher.getTeacherUsername());
            String jwt = jwtUtils.generateToken(userDetails);
            return jwt;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Incorret username or pass");
        }
    }

}
