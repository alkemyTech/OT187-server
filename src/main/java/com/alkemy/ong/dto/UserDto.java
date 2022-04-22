package com.alkemy.ong.dto;

import com.alkemy.ong.entity.Role;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Integer receivedRoleId;
    private Role roleId;
    private LocalDate creationDate;
}