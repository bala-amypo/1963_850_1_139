package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {

    private String token;
    private String email;
    private Set<String> roles;

    // ✅ REQUIRED: default constructor
    public AuthResponse() {
    }

    // ✅ REQUIRED: existing constructor (DO NOT REMOVE)
    public AuthResponse(String token) {
        this.token = token;
    }

    // ✅ NEW constructor for login response
    public AuthResponse(String token, String email, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.roles = roles;
    }

    // ✅ getters & setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
