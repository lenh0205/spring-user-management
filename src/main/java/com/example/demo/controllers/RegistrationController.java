package com.example.demo.controllers;

import com.example.demo.services.UserService;
import models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto, Model model) {
        try {
            userService.register(userDto);
            model.addAttribute("success", "User registered successfully!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }
}
