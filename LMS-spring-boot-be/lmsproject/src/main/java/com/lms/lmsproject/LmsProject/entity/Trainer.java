package com.lms.lmsproject.LmsProject.entity;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trainerId;

    @Column( unique = true)
    private String trainerUsername;

    @Column(unique = true)
    private String trainerEmail;

    @Nonnull
    private String trainerPassword;

    @Nonnull
    private String expertise; // Area of expertise, e.g., "Java", "Machine Learning"

    private List<String> role;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

}
