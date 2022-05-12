package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String firstName;
    private String email;
    private String role;
    private String jwt;
}
