package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;

import java.util.List;

public interface ContactService {
    
    ContactDto save(ContactDto dto);
    void delete(Long id);
    ContactDto findById(Long id);
    List<ContactDto> getAll();
}
