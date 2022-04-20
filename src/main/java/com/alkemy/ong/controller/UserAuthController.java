package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import com.alkemy.ong.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("auth/")
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Optional<User> userOptional = userRepository.findByEmail(authenticationRequest.getEmail());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (userOptional.isPresent()) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

            if (passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                );

                String jwt = jwtUtils.generateToken(userDetails);

                authenticationResponse.setJwt(jwt);
                authenticationResponse.setFirstName(userOptional.get().getFirstName());
                authenticationResponse.setEmail(userOptional.get().getEmail());
                authenticationResponse.setRole(userOptional.get().getRole.getName);
            } else {
                return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password doesn't match");
            }

            return ResponseEntity.ok().body(authenticationResponse);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}