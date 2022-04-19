package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    }
    
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = userMapper.convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.convertToDTO(savedUser);
    }
    
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
    
    @Transactional
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }
}
