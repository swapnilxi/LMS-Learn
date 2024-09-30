package com.lms.lmsproject.LmsProject.controllers;

import java.util.Arrays;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.UserEnt;
import com.lms.lmsproject.LmsProject.repository.UserEntRepo;
import com.lms.lmsproject.LmsProject.service.UserDetailsServiceImpl;
import com.lms.lmsproject.LmsProject.service.UserEntService;
import com.lms.lmsproject.LmsProject.utils.JwtUtils;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    private UserEntRepo userEntRepo;

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
        user.setRoles(Arrays.asList("USER"));
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userEntRepo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEnt user) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserName(), user.getUserPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUserName());
            String jwt = jwtUtils.generateToken(userDetails);
            return jwt;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Incorret username or pass");
        }
    }

    @GetMapping("/all")
    public List<UserEnt> getAllUsers() {
        return userEntService.getAllUsers();
    }

}
