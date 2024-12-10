package com.example.demo.services;

import com.example.demo.entities.Authority;
import com.example.demo.entities.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        // Create and save authority
        Authority authority = new Authority();
        authority.setUsername(userDto.getUsername());
        String role = userDto.getRole() != null && !userDto.getRole().isEmpty()
                ? userDto.getRole() : "ROLE_USER";
        authority.setAuthority(role);
        authorityRepository.save(authority);
    }
}
