package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;

public interface UserService {
    
    UserDto save(UserDto userDto);
    void delete(Integer id);
    User findById(Integer id);
}
