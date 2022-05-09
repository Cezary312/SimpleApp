package com.example.social_network.post;

import com.example.social_network.user.User;
import com.example.social_network.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostService
{
    PostRepository postRepository;

    UserRepository userRepository;
    ObjectMapper objectMapper;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ObjectMapper objectMapper)
    {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestHeader("userName") String userName, @RequestBody String postContent)
    {
        Optional<User> userFromDb = userRepository.findByUserName(userName);
        if(userFromDb.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Post savedPost = new Post(userFromDb.get(), postContent);

        postRepository.save(savedPost);

        return ResponseEntity.ok(savedPost);
    }

    @GetMapping("/posts")
    public ResponseEntity getPosts(@RequestHeader("userName") String userName)
    {
        Optional<User> userFromDb = userRepository.findByUserName(userName);
        if(userFromDb.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Post> allPosts = postRepository.findAll();
        return ResponseEntity.ok(allPosts);

    }
}
