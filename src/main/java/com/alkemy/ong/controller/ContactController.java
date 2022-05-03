package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alkemy.ong.utility.Constantes.CONTACT_URL;

@RestController
@RequestMapping(CONTACT_URL)
@AllArgsConstructor
public class ContactController {

    @Autowired
    private ContactService contactService;
    
    @GetMapping()
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAll());
    }


}
