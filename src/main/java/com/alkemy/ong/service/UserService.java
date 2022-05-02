package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void delete(Integer id);
    User findById(Integer id);
    List<User> getAllUsers();
    User findByEmail(String email);
}
