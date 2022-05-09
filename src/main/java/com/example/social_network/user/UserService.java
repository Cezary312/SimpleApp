package com.example.social_network.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserService
{
    UserRepository userRepository;
    ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper)
    {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/users")
    public ResponseEntity getUser() throws JsonProcessingException
    {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user)
    {
        Optional<User> userFromDb = userRepository.findByUserName(user.getUserName());
        if(userFromDb.isPresent())
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user)
    {
        Optional<User> userFromDb = userRepository.findByUserName(user.getUserName());

        if(userFromDb.isEmpty() || validatePassword(userFromDb, user))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok().build();
    }

    private boolean validatePassword(Optional<User> userFromDb, User user)
    {
        return !userFromDb.get().getPassword().equals(user.getPassword());
    }
}
