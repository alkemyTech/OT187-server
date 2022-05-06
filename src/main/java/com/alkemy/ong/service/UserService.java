package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);
    void delete(Integer id);
    UserDto findById(Integer id);
    List<UserDto> getAllUsers();
    UserDto findByEmail(String email);
}
