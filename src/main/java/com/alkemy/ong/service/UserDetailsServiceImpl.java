package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.InvalidDTOException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    
    public UserDetailsServiceImpl(UserMapper userMapper, UserRepository userRepository) {
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
        userRepository.softDelete(id);
    }
    
    @Transactional
    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    private void validateReceivedDTO(UserDto dto) {
    
        if (dto == null) {
            throw new InvalidDTOException("No character was received");
        }
        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            throw new InvalidDTOException("");
        }
        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getPhoto() == null || dto.getPhoto().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getReceivedRoleId() == null) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidDTOException("Character must have an image");
        }
        if (dto.getCreationDate() == null) {
            throw new InvalidDTOException("Character must have an image");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user=userRepository.findByFirstName(firstName).orElse(null);

        if(user==null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Role role=user.getRoleId();
        List<GrantedAuthority> authority= Stream.of(role).map(role1 -> new SimpleGrantedAuthority(role1.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getFirstName(),user.getPassword(),authority);
    }
}
