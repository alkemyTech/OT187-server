package com.alkemy.ong.controller;

import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping()
    public ResponseEntity<?> getMembers(){
        Map<String,Object> response=new HashMap<>();

        response.put("members",memberService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
