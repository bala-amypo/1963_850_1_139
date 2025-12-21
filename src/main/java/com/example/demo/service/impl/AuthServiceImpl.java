package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider; // Intha file neenga security-la create panni irukanum
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Rule: Constructor injection only
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Override
    public User register(User user) {
        // UserService-la irukka registerUser logic-ah call panrom
        return userService.registerUser(user);
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        // Step 4.6: Login logic via AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT Token generate panrom
        String jwt = tokenProvider.generateToken(authentication);

        // AuthResponse DTO form-la return panrom
        AuthResponse response = new AuthResponse();
        response.setAccessToken(jwt);
        response.setEmail(authRequest.getEmail());
        response.setTokenType("Bearer");
        
        return response;
    }
}