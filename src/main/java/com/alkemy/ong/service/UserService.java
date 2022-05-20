package com.alkemy.ong.service;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.RegisterDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    RegisterDto save(UserDto userDto);
    void delete(Integer id);
    UserDto findById(Integer id);
    List<UserDto> getAllUsers();
    UserDto findByEmail(String email);
    ResponseEntity login(AuthenticationRequest authenticationRequest);
}
