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

import com.lms.lmsproject.LmsProject.entity.Role;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.repository.TeacherRepo;
import com.lms.lmsproject.LmsProject.utils.JwtUtils;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    private Teacher loggedInTeacher;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public String loginTeacher(Teacher teacher) {
        // Fetch the teacher by username using Optional
        Optional<Teacher> optionalTeacher = teacherRepo.findByTeacherUsername(teacher.getTeacherUsername());

        if (optionalTeacher.isPresent()) {
            // Authenticate using the found teacher's credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    teacher.getTeacherUsername(),
                    teacher.getTeacherPassword()));

            // Load UserDetails and generate JWT
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(teacher.getTeacherUsername());
            String jwt = jwtUtils.generateToken(userDetails);
            return jwt;

        } else {
            throw new UsernameNotFoundException("Incorrect username or password");
        }
    }

    public Teacher getAuthenticatedTeacher() {
        if (loggedInTeacher == null) {
            String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUsername();
            loggedInTeacher = teacherRepo.findByTeacherUsername(username).get();
            if (loggedInTeacher == null) {
                throw new UsernameNotFoundException("User Not Found");
            }
        }
        return loggedInTeacher;
    }

    public List<Teacher> fetchAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher createNewTeacher(Teacher reqTeacher) {
        if (reqTeacher.getTeacherUsername() == null || reqTeacher.getTeacherUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher Name can Not be Null");
        }
        if (reqTeacher.getTeacherEmail() == null || reqTeacher.getTeacherEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher Email can Not be Null");
        }
        if (reqTeacher.getTeacherPassword() == null || reqTeacher.getTeacherPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher Password can Not be Null");
        }
        if (reqTeacher.getExpertise() == null || reqTeacher.getExpertise().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher Expertise can Not be Null");
        }

        Teacher newTeacher = Teacher.builder()
                .teacherUsername(reqTeacher.getTeacherUsername())
                .teacherEmail(reqTeacher.getTeacherEmail())
                .teacherPassword(reqTeacher.getTeacherPassword())
                .expertise(reqTeacher.getExpertise())
                .teacherPassword(passwordEncoder.encode(reqTeacher.getTeacherPassword()))
                .roles(Set.of(Role.TEACHER))
                .build();

        return teacherRepo.save(newTeacher);
    }

    public Teacher findByTeacherEmail(String email) {
        if (teacherRepo.findByTeacherEmail(email).isPresent()) {
            return teacherRepo.findByTeacherEmail(email).get();
        } else {
            throw new UsernameNotFoundException("Teacher with this Email Not Found !");
        }
    }

    // public Teacher updateTeacher(Teacher reqTeacher) {
    
    //     Teacher updatedTeacher = Teacher.builder()
    //             .teacherId(getAuthenticatedTeacher().getTeacherId())
    //             .teacherUsername(reqTeacher.getTeacherUsername())
    //             .teacherEmail(reqTeacher.getTeacherEmail())
    //             .teacherPassword(reqTeacher.getTeacherPassword())
    //             .expertise(reqTeacher.getExpertise())
    //             .roles(Set.of(Role.TEACHER))
    //             .build();
    
    //     return teacherRepo.save(updatedTeacher);
    // }
    

    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("This teacher id Does not Exist"));

        if (!teacher.getTeacherId().equals(getAuthenticatedTeacher().getTeacherId())) {
            throw new IllegalArgumentException("You can only delete your own profile");
        }
        teacherRepo.deleteById(id);
    }
}
