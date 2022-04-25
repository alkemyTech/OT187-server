package com.alkemy.ong.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String firstName;
    private String email;
    private String role;
    private String jwt;
}
