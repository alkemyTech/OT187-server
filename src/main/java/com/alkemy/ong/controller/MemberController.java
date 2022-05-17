package com.alkemy.ong.controller;

import com.alkemy.ong.dto.PageResponseDto;
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
    public ResponseEntity<?> getMembers(@RequestParam(value = "page", defaultValue = "1") int page) {
        PageResponseDto pageResponse = memberService.getAll(page);
        return ResponseEntity.ok().body(pageResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMembers(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new HashMap<>();
        memberService.deleteById(id);
        response.put("message", "Member has been successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

