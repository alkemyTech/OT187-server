package com.alkemy.ong.controller;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import com.alkemy.ong.utility.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Login an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. User logged in",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Password doesn't match",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PostMapping(LOGIN_URL)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return userDetailsService.login(authenticationRequest);
    }

    @Operation(summary = "Register an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. User registered",
                    content = {
                            @Content(mediaType = "string/plain",
                                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "User not found",
                    content = @Content)})
    @PostMapping(REGISTER_URL)
    public ResponseEntity<String> register(@Valid @RequestBody UserDto userDto){
        userDetailsService.save(userDto);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        String jwt = jwtUtils.generateToken(userDetails);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Return all users",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))})
    })
    @GetMapping(USER_GET)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userDetailsService.getAllUsers(), HttpStatus.OK);
    }
    @Operation(summary = "Update an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. User updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "User not found",
                    content = @Content)})
    @PatchMapping(USER_PATCH)
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") Integer id){
        return new ResponseEntity<>(userDetailsService.update(userDto, id), HttpStatus.OK);
    }

    @Operation(summary = "Get an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Return user",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "403", description = "Jwt token not valid",
                    content = @Content)})
    @GetMapping(USER_AUTH_ME)
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){
        String tokenObtenido = token.replace("Bearer ", "");
        String email = jwtUtils.extractUsername(tokenObtenido);
     return new ResponseEntity<>(userDetailsService.findByEmail(email),HttpStatus.OK);
    }


}