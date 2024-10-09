package com.lms.lmsproject.LmsProject.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.lmsproject.LmsProject.entity.Role;
import com.lms.lmsproject.LmsProject.entity.Teacher;
import com.lms.lmsproject.LmsProject.repository.TeacherRepo;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Teacher> fetchAllTeachers() {
        return teacherRepo.findAll();
    }

    public void createNewTeacher(Teacher teacher) {
        teacher.setRoles(Set.of(Role.TEACHER));
        teacher.setPosts(null);
        teacher.setCourses(null);
        teacher.setTeacherPassword(passwordEncoder.encode(teacher.getTeacherPassword()));
        teacherRepo.save(teacher);
    }

}
