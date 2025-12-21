package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
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
        // Rule: Password encoding and user creation in service layer
        return userService.registerUser(user);
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        // Step 4.6 & Rule 7.1: Authenticate via AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT Token generate panrom using the email/username
        String jwt = tokenProvider.generateToken(authentication.getName());

        // Extracting the Role (Dataset Rule 7.1 requirement)
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst()
                .orElse("ROLE_USER");

        // Building the AuthResponse
        AuthResponse response = new AuthResponse();
        response.setAccessToken(jwt);
        response.setEmail(authRequest.getEmail());
        response.setRole(role); // Now the role is included!
        response.setTokenType("Bearer");
        
        return response;
    }
}