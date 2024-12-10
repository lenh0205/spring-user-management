package com.example.demo.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 50, nullable = false)
    private String username; // Primary key matches the `users` table schema

    @Column(length = 100, nullable = false)
    private String password; // Matches the `users` table schema

    @Column(nullable = false)
    private boolean enabled; // Matches the `users` table schema

    // No direct mapping for authorities here, handled via a separate table

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
