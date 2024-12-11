package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String Test() {
        User user = userRepository.findByUsername("user1")
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getPassword();
    }
}
