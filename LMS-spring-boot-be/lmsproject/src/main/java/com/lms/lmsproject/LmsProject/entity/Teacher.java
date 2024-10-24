package com.lms.lmsproject.LmsProject.entity;

import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.annotation.Nonnull;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.ElementCollection;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Teacher {

    @Id
    private ObjectId teacherId;

    @Field("teacherUsername")
    @Nonnull
    private String teacherUsername;

    @Field("teacherEmail")
    @Nonnull
    private String teacherEmail;

    @Nonnull
    private String teacherPassword;

    @DBRef
    private List<Post> posts; // Change List<Course> to List<Post>

    @Nonnull
    private String expertise; // Area of expertise, e.g., "Java", "Machine Learning"

    private Set<Role> roles;

    @DBRef
    private List<Course> courses;

}
