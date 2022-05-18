package com.alkemy.ong.auth.dto;

import com.alkemy.ong.auth.domain.RoleDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private RoleDomain roleId;
    @JsonIgnore
    private String password;
    private String photo;
    private LocalDateTime timestamps;
}
