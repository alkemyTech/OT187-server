package com.alkemy.ong.service;


import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.EmailExistsException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UserDto save(UserDto userDto) throws EmailExistsException {
        User user = userMapper.userDtoToUser(userDto);
        if (emailExist(user.getEmail())) {
            throw new EmailExistsException("An account with the email address "
                    + user.getEmail() + " already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleId(roleRepository.findByName("USER"));
        userRepository.save(user);
        return userMapper.userToUserDto(user);

    }


    @Transactional
    public void delete(Integer id) {
        userRepository.softDelete(id);
    }

    @Transactional
    @Override
    public UserDto findById(Integer id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow());
    }

    @Transactional
    @Override
    public UserDto findByEmail(String email){
        return userMapper.userToUserDto(userRepository.findByEmail(email).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Role role = user.getRoleId();
        List<GrantedAuthority> authority = Stream.of(role).map(role1 -> new SimpleGrantedAuthority(role1.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authority);
    }

    private boolean emailExist(String emailAccount) {
        Optional<User> userFound = userRepository.findByEmail(emailAccount);
        return userFound.isPresent();
    }

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.allUserstoAllUsersDto(userRepository.findAll());
    }
}



