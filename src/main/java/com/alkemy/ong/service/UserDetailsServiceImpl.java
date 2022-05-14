package com.alkemy.ong.service;


import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.EmailExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final JwtUtils jwtUtils;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtUtils jwtUtils) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    @Override
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

    public UserDto update(UserDto userDto, Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhoto(userDto.getPhoto());

        User savedUser = userRepository.save(user);

        return userMapper.userToUserDto(savedUser);

    }

    @Override
    @Transactional
    public ResponseEntity login(AuthenticationRequest authenticationRequest) {

        Optional<User> user = userRepository.findByEmail(authenticationRequest.getEmail());

        if (user.isPresent() && user.get().getActive() == 1) {
            UserDetails userDetails = loadUserByUsername(authenticationRequest.getEmail());

            if (passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {

                AuthenticationResponse authenticationResponse =
                        new AuthenticationResponse(user.get().getFirstName(), user.get().getEmail(), user.get().getRoleId().getName(), jwtUtils.generateToken(userDetails));

                return ResponseEntity.ok().body(authenticationResponse);

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password doesn't match");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
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



