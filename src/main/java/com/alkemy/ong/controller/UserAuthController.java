package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import com.alkemy.ong.utility.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.utility.Constantes.*;

@RestController
@RequestMapping(AUTH_URL)
public class UserAuthController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return userDetailsService.login(authenticationRequest);
    }


    @PostMapping(REGISTER_URL)
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){
        userDetailsService.save(userDto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        String jwt = jwtUtils.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping(USER_GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userDetailsService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(USER_PATCH)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
        UserDto userUpdated = userDetailsService.findById(id);
        return new ResponseEntity<>(userDetailsService.save(userUpdated), HttpStatus.OK);
    }
    @GetMapping(USER_AUTH_ME)
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){
        String tokenObtenido = token.replace("Bearer", " ");
        String email = jwtUtils.extractUsername(tokenObtenido);
     return new ResponseEntity<>(userDetailsService.findByEmail(email),HttpStatus.OK);
    }



}