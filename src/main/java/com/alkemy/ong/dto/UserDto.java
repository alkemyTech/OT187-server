package com.alkemy.ong.dto;

import com.alkemy.ong.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Role roleId;
    private LocalDate creationDate;
}
