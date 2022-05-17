package com.alkemy.ong;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ContactService {

    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp()
    {
        contactRepository.deleteAll();
    }

    @AfterAll
    public void cleanUp()
    {
        contactRepository.deleteAll();
    }

    @Test
    public void saveContact()
    {
        ContactDto contactSavedDto = contactService.save(new ContactDto(
                "Contact_1",
                "+5491157634228",
                "contact_1_email@gmail.com",
                "This is a test contact"
        ));
        Assertions.assertNotNull(contactSavedDto.getId());
    }
}
