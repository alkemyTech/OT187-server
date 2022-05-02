package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.alkemy.ong.utility.Constantes.*;

@RestController
@RequestMapping(AUTH_URL)
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

    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Optional<User> userOptional = userRepository.findByEmail(authenticationRequest.getEmail());

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (userOptional.isPresent() && (userOptional.get().getActive() == 1)) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

            if (passwordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                );

                String jwt = jwtUtils.generateToken(userDetails);

                authenticationResponse.setJwt(jwt);
                authenticationResponse.setFirstName(userOptional.get().getFirstName());
                authenticationResponse.setEmail(userOptional.get().getEmail());
                authenticationResponse.setRole(userOptional.get().getRoleId().getName());
            } else {
                return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password doesn't match");
            }

            return ResponseEntity.ok().body(authenticationResponse);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    @PostMapping(REGISTER_URL)
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){
        User user = userMapper.userDtoToUser(userDto);
        userDetailsService.save(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String jwt = jwtUtils.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping(USER_GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userMapper.allUserstoAllUsersDto(userDetailsService.getAllUsers()), HttpStatus.OK);
    }

    @PatchMapping(USER_PATCH)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
        User userUpdated = userMapper.updateUserFromDto(userDto, userDetailsService.findById(id));
        userDetailsService.save(userUpdated);
        return new ResponseEntity<>(userMapper.userToUserDto(userUpdated), HttpStatus.OK);
    }
    @GetMapping(USER_AUTH_ME)
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){
        String tokenObtenido = token.replace("Bearer", " ");
        String email = jwtUtils.extractUsername(tokenObtenido);
        User user = userDetailsService.findByEmail(email);
     return new ResponseEntity<>(userMapper.userToUserDto(user),HttpStatus.OK);
    }



}