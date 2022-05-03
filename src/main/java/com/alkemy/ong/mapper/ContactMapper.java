package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    
    Contact contactDtoToContact(ContactDto contactDto);
    ContactDto contactToContactDto(Contact contact);
    List<ContactDto> listContactToListContactDto(List<Contact>list);
}
