package com.example.demo.dto;

import lombok.Data;

@Data // Lombok automatically getters and setters generate pannum
public class UserDTO {
    private String email;
    private String password;
    private String role;
}