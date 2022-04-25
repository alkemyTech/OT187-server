package com.alkemy.ong.controller;

import com.alkemy.ong.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.utility.Constantes.USER_URL;

@RestController
@RequestMapping(USER_URL)
public class UserController {

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id)
    {
        userDetailsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
