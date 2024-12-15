package com.example.demo.services;

import com.example.demo.entities.Authority;
import com.example.demo.entities.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void register(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        // Create and save user
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

        // Assign default authorities
        List<String> defaultAuthorities = Arrays.asList("ROLE_USER", "SCOPE_read.message", "SCOPE_write.message");
        for (String authority : defaultAuthorities) {
            Authority authorityRecord = new Authority();
            authorityRecord.setUsername(userDto.getUsername());
            authorityRecord.setAuthority(authority);
            authorityRepository.save(authorityRecord);
        }
    }
}
