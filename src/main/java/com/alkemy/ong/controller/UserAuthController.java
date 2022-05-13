package com.alkemy.ong.controller;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import com.alkemy.ong.utility.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.alkemy.ong.utility.Constantes.*;

@Tag(name = "User")
@RestController
@RequestMapping(AUTH_URL)
public class UserAuthController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login succesful"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"),
            @ApiResponse(
                    responseCode = "401",
                    description = "Password doesn't match"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request")
    })
    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return userDetailsService.login(authenticationRequest);
    }

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered succesfully")
    })
    @PostMapping(REGISTER_URL)
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){
        userDetailsService.save(userDto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        String jwt = jwtUtils.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @Operation(summary = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All users found succesfully",
                    content = { @Content(array = @ArraySchema(
                            schema = @Schema(implementation = UserDto.class))) })
    })
    @GetMapping(USER_GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userDetailsService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request")
    })
    @PatchMapping(USER_PATCH)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
        return new ResponseEntity<>(userDetailsService.update(userDto, id), HttpStatus.OK);
    }

    @Operation(summary = "Find logged in user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logged in user found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) })
    })
    @GetMapping(USER_AUTH_ME)
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){
        String tokenObtenido = token.replace("Bearer ", "");
        String email = jwtUtils.extractUsername(tokenObtenido);
     return new ResponseEntity<>(userDetailsService.findByEmail(email),HttpStatus.OK);
    }
}