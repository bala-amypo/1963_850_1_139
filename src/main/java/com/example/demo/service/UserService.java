package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.Optional;

public interface UserService {
    // Used for Rule 6.1: Register and encrypt password
    User registerUser(User user);
    
    // Used for Rule 8.1: Find user for login/JWT
    User findByEmail(String email);
}