package com.alkemy.ong.controller;

import com.alkemy.ong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.alkemy.ong.utility.Constantes.MEMBER_URL;

@RestController
@RequestMapping(MEMBER_URL)
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getMembers(){
        Map<String,Object> response=new HashMap<>();

        response.put("members",memberService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id")Long id){
        Map<String,Object> response=new HashMap<>();
        memberService.deleteById(id);
        response.put("mensaje","El miembro ha sido eliminado con exito");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
