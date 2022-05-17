package com.alkemy.ong;

import com.alkemy.ong.dto.AuthenticationRequest;
import com.alkemy.ong.dto.AuthenticationResponse;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.mapper.RoleMapper;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.web.util.NestedServletException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static com.alkemy.ong.utility.Constantes.*;


@AutoConfigureMockMvc(addFilters = false)       // This is to disable the API security
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserController {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserDetailsServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @BeforeAll
    public void setUpAll()
    {
        roleRepository.save(new Role("USER", "Regular user", LocalDate.of(2022, 5, 12)));
    }

    @BeforeEach
    public void setUp()
    {
        userRepository.deleteAll();
    }

    @AfterAll
    public void cleanUp()
    {
        userRepository.deleteAll();
    }

    @Test
    public void saveUser() throws Exception
    {
        UserDto userDto = new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        );

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post(AUTH_URL + REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        String responseBody = response.getResponse().getContentAsString(StandardCharsets.UTF_8);
        //System.out.println(responseBody);
        Assertions.assertTrue(responseBody.startsWith("ey"));
        Assertions.assertEquals(179, responseBody.length());
    }

    @Test
    public void saveUserRepeatedEmail() throws Exception
    {
        UserDto userDto = addUserUsingService();

        UserDto userRepeatedEmailDto = new UserDto(
                "Martin",
                "Gimenez",
                "emailgenerico1@email.com",
                "contraseña2",
                "http://imagen2.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        );

        Assertions.assertThrows(NestedServletException.class, () ->
                mockMvc.perform(MockMvcRequestBuilders
                                .post(AUTH_URL + REGISTER_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRepeatedEmailDto)))
                                .andDo(MockMvcResultHandlers.print())
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andReturn()
        );
    }

    @Test
    public void updateUser() throws Exception
    {
        UserDto userDto = addUserUsingService();
        Assertions.assertEquals("emailgenerico1@email.com", userDto.getEmail());

        userDto.setEmail("user_updated_email@test.com");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .patch(AUTH_URL + "/users/" + userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        UserDto responseAsObject = objectMapper.readValue(response.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDto.class);
        Assertions.assertEquals("user_updated_email@test.com", responseAsObject.getEmail());
    }

    @Test
    public void getAll() throws Exception
    {
        addUserUsingService();
        userService.save(new UserDto(
                "Pablo",
                "Martinez",
                "emailgenerico2@email.com",
                "contraseña2",
                "http://imagen2.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));
        userService.save(new UserDto(
                "Martin",
                "Gimenez",
                "emailgenerico3@email.com",
                "contraseña3",
                "http://imagen3.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .get(AUTH_URL + USER_GET)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        List<UserDto> responseAsObjet =
                objectMapper.readValue(response.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<List<UserDto>>(){});
        Assertions.assertFalse(responseAsObjet.isEmpty());
        Assertions.assertEquals(3, responseAsObjet.size());
    }

    @Test
    public void getUserByToken() throws Exception
    {
        UserDto userDto = new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        );

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post(AUTH_URL + REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        String responseBody = response.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Assertions.assertTrue(responseBody.startsWith("ey"));
        Assertions.assertEquals(179, responseBody.length());

        MvcResult responseGet = mockMvc.perform(MockMvcRequestBuilders
                        .get(AUTH_URL + USER_AUTH_ME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", responseBody))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(responseGet.getResponse().getContentAsString().isEmpty());
        UserDto responseAsObject = objectMapper.readValue(responseGet.getResponse().getContentAsString(StandardCharsets.UTF_8), UserDto.class);
        Assertions.assertEquals(userDto.getEmail(), responseAsObject.getEmail());
    }

    @Test
    public void login() throws Exception
    {
        addUserUsingService();

        AuthenticationRequest authenticationRequest = new AuthenticationRequest(
                "emailgenerico1@email.com",
                "contraseña1"
        );

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                        .post(AUTH_URL + LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authenticationRequest)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

        Assertions.assertFalse(response.getResponse().getContentAsString().isEmpty());
        AuthenticationResponse responseAsObject = objectMapper.readValue(response.getResponse().getContentAsString(StandardCharsets.UTF_8), AuthenticationResponse.class);
        Assertions.assertEquals(authenticationRequest.getEmail(), responseAsObject.getEmail());
        Assertions.assertTrue(responseAsObject.getJwt().startsWith("ey"));
        Assertions.assertEquals(179, responseAsObject.getJwt().length());
    }


    private UserDto addUserUsingService()
    {
        UserDto userDto = new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        );

        return userService.save(userDto);
    }
}
