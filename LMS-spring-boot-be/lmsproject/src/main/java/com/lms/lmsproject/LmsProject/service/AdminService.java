package com.lms.lmsproject.LmsProject.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.entity.Admin;
import com.lms.lmsproject.LmsProject.entity.Role;
import com.lms.lmsproject.LmsProject.repository.AdminRepo;
import com.lms.lmsproject.LmsProject.utils.JwtUtils;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepoService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Admin loggedAdmin;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public String loginAdmin(Admin admin) {
        Optional<Admin> optionalUser = adminRepoService.findByAdminName(admin.getAdminName());

        if (optionalUser.isPresent()) {
            // Authenticate using the found user's credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    admin.getAdminName(),
                    admin.getAdminPassword()));

            // Load UserDetails and generate JWT
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(admin.getAdminName());
            String jwt = jwtUtils.generateToken(userDetails);
            return jwt;

        } else {
            throw new UsernameNotFoundException("Incorrect username or password");
        }
    }

    public Admin getAuthenticatedAdmin() {
        if (loggedAdmin == null) {
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUsername();
            loggedAdmin = adminRepoService.findByAdminName(username).get();
            if (loggedAdmin == null) {
                throw new UsernameNotFoundException("User is Not Found !");
            }
        }
        return loggedAdmin;
    }

    public List<Admin> getAllAdmins() {
        return adminRepoService.findAll();
    }

    public Admin createNewAdmin(Admin reqAdmin) {

        if (reqAdmin.getAdminName() == null || reqAdmin.getAdminName().trim().isEmpty()) {
            throw new IllegalArgumentException("UserName can Not be Null");
        }
        if (reqAdmin.getAdminEmail() == null || reqAdmin.getAdminEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User Email can Not be Null");
        }
        if (reqAdmin.getAdminPassword() == null || reqAdmin.getAdminPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("User Password can Not be Null");
        }

        // Check if the adminEmail is already registered
        Optional<Admin> existingAdmin = adminRepoService.findByAdminEmail(reqAdmin.getAdminEmail());
        if (existingAdmin.isPresent()) {
            throw new IllegalArgumentException("Admin with this email is already registered");
        }

        Admin newAdmin = Admin.builder()
                .adminEmail(reqAdmin.getAdminEmail())
                .adminName(reqAdmin.getAdminName())
                .adminPassword(passwordEncoder.encode(reqAdmin.getAdminPassword()))
                .roles(Set.of(Role.ADMIN))
                .build();

        return adminRepoService.save(newAdmin);
    }

    public Admin findAdminByEmail(String email) {
        if (adminRepoService.findByAdminEmail(email).isPresent()) {
            return adminRepoService.findByAdminEmail(email).get();
        } else {
            throw new UsernameNotFoundException("Admin with this Email Not Found !");
        }
    }

    public Admin updateAdmin(Admin reqAdmin) {
        Admin authenticatedAdmin = getAuthenticatedAdmin(); // Get the currently logged-in admin
    
        // Ensure that the admin is found and has a non-null ID
        if (authenticatedAdmin.getAdminId() == null) {
            throw new IllegalArgumentException("Authenticated Admin ID is null");
        }
    
        // Proceed to update the admin
        Admin updatedAdmin = Admin.builder()
                .adminId(authenticatedAdmin.getAdminId()) // Make sure to set the ID
                .adminEmail(reqAdmin.getAdminEmail())
                .adminName(reqAdmin.getAdminName())
                .adminPassword(passwordEncoder.encode(reqAdmin.getAdminPassword()))
                .roles(Set.of(Role.ADMIN))
                .build();
    
        return adminRepoService.save(updatedAdmin);
    }
    

    public void deleteAdminById(Long id) {
        Admin admin = adminRepoService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found !"));

        // if (!admin.getAdminId().equals(getAuthenticatedAdmin().getAdminId())) {
        //     throw new IllegalArgumentException("You are not authorized to delete this Admin");
        // }
        adminRepoService.delete(admin);
    }

}
