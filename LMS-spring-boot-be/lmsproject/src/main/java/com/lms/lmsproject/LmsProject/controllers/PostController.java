package com.lms.lmsproject.LmsProject.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.lmsproject.LmsProject.entity.Post;
import com.lms.lmsproject.LmsProject.service.PostService;

@RestController
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/all-post")
    public List<Post> getAllPosts() {
        return postService.fetchAllPost();
    }

    @PostMapping(path = "/create-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping(path = "/findpost/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Post post = postService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

}
