package com.alkemy.ong;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.entity.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


@AutoConfigureMockMvc(addFilters = false)       // El addFilters es para desabilitar la seguridad de la API
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ContactController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
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
    public void saveContact() throws Exception
    {
        ContactDto contactDto = new ContactDto(
                "Contact_1",
                "+5491157634228",
                "contact_1_email@gmail.com",
                "This is a test contact"
        );

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contactDto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isCreated())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        ContactDto responseAsObject = objectMapper.readValue(response.getResponse().getContentAsString(StandardCharsets.UTF_8), ContactDto.class);
        Assertions.assertNotNull(responseAsObject.getId());

        Optional<Contact> contactSaved = contactRepository.findById(responseAsObject.getId());
        Assertions.assertTrue(contactSaved.isPresent());
        Assertions.assertEquals(responseAsObject.getId(), contactSaved.get().getId());  // Esta asercion es redundante
    }
}
