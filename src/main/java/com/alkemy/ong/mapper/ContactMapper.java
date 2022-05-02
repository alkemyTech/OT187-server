package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    
    Contact contactDtoToContact(ContactDto contactDto);
    ContactDto contactToContactDto(Contact contact);
}
