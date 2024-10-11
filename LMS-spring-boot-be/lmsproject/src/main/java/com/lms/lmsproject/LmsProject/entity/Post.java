package com.lms.lmsproject.LmsProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    // @Nonnull
    private String title;

    // @Nonnull
    private String content;

    @Enumerated(EnumType.STRING)
    private PostEnu catagories;

    @ManyToOne
    @JsonIgnore
    private Teacher teacher; // Reference to the Teacher entity

    private String teacherName;

}
