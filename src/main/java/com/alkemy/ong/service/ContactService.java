package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;

public interface ContactService {
    
    ContactDto save(ContactDto dto);
    void delete(Long id);
    ContactDto findById(Long id);
}
