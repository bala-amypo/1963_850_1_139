package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    User register(User user);
}