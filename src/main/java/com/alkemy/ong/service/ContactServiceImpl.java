package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.ContactMapper;
import com.alkemy.ong.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }
    
    @Transactional
    @Override
    public ContactDto save(ContactDto dto) {
        Contact contact = contactMapper.contactDtoToContact(dto);
        Contact savedContact = contactRepository.save(contact);
        
        return contactMapper.contactToContactDto(savedContact);
    }
    
    @Transactional
    @Override
    public void delete(Long id) {
        contactRepository.softDelete(id);
    }
    
    @Transactional
    @Override
    public ContactDto findById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(RuntimeException::new);
        return contactMapper.contactToContactDto(contact);
    }

    @Override
    public List<ContactDto> getAll() {
        List<Contact> contacts = contactRepository.findAllActive();
        if(contacts.isEmpty()) {
            throw new NotFoundException("No contacts found");
        }
        return contactMapper.listContactToListContactDto(contacts);
    }
}
