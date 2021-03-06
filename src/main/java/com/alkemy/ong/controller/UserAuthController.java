package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.RegisterDto;
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
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto){
        try {
            UserDto userSaved = userDetailsService.save(userDto);

            String jwt = jwtUtils.generateToken(userDetailsService.loadUserByUsername(userDto.getEmail()));

            return new ResponseEntity<>(new RegisterDto(userSaved.getFirstName(), userSaved.getLastName(), userSaved.getEmail(), jwt), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(USER_GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userDetailsService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(USER_PATCH)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
        return new ResponseEntity<>(userDetailsService.update(userDto, id), HttpStatus.OK);
    }

    @GetMapping(USER_AUTH_ME)
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){
        String tokenObtenido = token.replace("Bearer ", "");
        String email = jwtUtils.extractUsername(tokenObtenido);
     return new ResponseEntity<>(userDetailsService.findByEmail(email),HttpStatus.OK);
    }

}