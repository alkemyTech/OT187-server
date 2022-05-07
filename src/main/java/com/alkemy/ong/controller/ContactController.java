package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.alkemy.ong.utility.Constantes.CONTACT_URL;

@RestController
@RequestMapping(CONTACT_URL)
@AllArgsConstructor
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @GetMapping()
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAll());
    }
    
    @PostMapping()
    public ResponseEntity<ContactDto> save(@Valid @RequestBody ContactDto contactDto)
    {
        ContactDto savedContact = contactService.save(contactDto);

        try {
            emailServiceImpl.contactEmail(contactDto.getEmail());
        } catch (IOException e) {
            System.out.println("Error while sending email");
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }
}
