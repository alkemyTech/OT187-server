package com.alkemy.ong.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String jwt;
}
