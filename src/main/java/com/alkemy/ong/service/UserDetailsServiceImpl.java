package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.EmailExistsException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public UserDto save(UserDto userDto) throws EmailExistsException{
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException("An account with the email address "
                    + userDto.getEmail() + " already exists.");
        }
        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        Role role=user.getRoleId();
        List<GrantedAuthority> authority= Stream.of(role).map(role1 -> new SimpleGrantedAuthority(role1.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authority);
    }
    
    private boolean emailExist(String emailAccount) {
        Optional<User> userFound = userRepository.findByEmail(emailAccount);
        return userFound.isPresent();
    }

    @Transactional
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
