package com.alkemy.ong;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.EmailExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.RoleMapper;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserService {

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
        roleRepository.deleteAll();
    }


    @Test
    public void saveUser()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));
        Assertions.assertNotNull(userSavedDto.getId());
    }

    @Test
    public void saveUserRepeatedEmail()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertNotNull(userSavedDto.getId());
        Assertions.assertThrows(EmailExistsException.class, () ->
                userService.save(new UserDto(
                        "Pepito",
                        "Rodriguez",
                        "emailgenerico1@email.com",
                        "contraseña2",
                        "http://imagen12gl",
                        roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
                ))
        );
    }

    @Test
    public void findUserById()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertDoesNotThrow( () -> userService.findById(userSavedDto.getId()) );
        UserDto userSearchedDto = userService.findById(userSavedDto.getId());
        Assertions.assertNotNull(userSearchedDto);
        Assertions.assertEquals(userSavedDto.getId(), userSearchedDto.getId());
    }

    @Test
    public void findUserByIdNonExistent()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.findById(9999));
    }

    @Test
    public void findUserByEmail()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertDoesNotThrow( () -> userService.findByEmail(userSavedDto.getEmail()) );
        UserDto userSearchedDto = userService.findByEmail(userSavedDto.getEmail());
        Assertions.assertNotNull(userSearchedDto);
        Assertions.assertEquals(userSavedDto.getEmail(), userSearchedDto.getEmail());
    }

    @Test
    public void findUserByEmailNonExistent()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.findByEmail("non_existent@email.com"));
    }

    @Test
    public void findAll()
    {
        userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));
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

        List<UserDto> usersDto = userService.getAllUsers();
        Assertions.assertTrue(!usersDto.isEmpty());
        Assertions.assertTrue(usersDto.size() == 3);
    }

    @Test
    public void updateUser()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        UserDto userSearchedDto = userService.findById(userSavedDto.getId());
        Assertions.assertNotNull(userSearchedDto);
        Assertions.assertEquals("emailgenerico1@email.com", userSearchedDto.getEmail());

        userSearchedDto.setEmail("user_updated_email@test.com");
        UserDto userUpdatedDto = userService.update(userSearchedDto, userSearchedDto.getId());
        UserDto userUpdatedSearchedDto = userService.findById(userUpdatedDto.getId());
        Assertions.assertNotNull(userUpdatedSearchedDto);
        Assertions.assertEquals("user_updated_email@test.com", userUpdatedSearchedDto.getEmail());
    }

    @Test
    public void updateUserNonExistent()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        UserDto userSearchedDto = userService.findById(userSavedDto.getId());
        Assertions.assertNotNull(userSearchedDto);
        Assertions.assertEquals("emailgenerico1@email.com", userSearchedDto.getEmail());

        userSearchedDto.setEmail("user_updated_email@test.com");
        Assertions.assertThrows(NotFoundException.class, () -> userService.update(userSearchedDto, 9999));
    }

    @Test
    public void deleteUser()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        User userSaved = userRepository.findById(userSavedDto.getId()).get();
        Assertions.assertEquals(1, userSaved.getActive());

        userService.delete(userSavedDto.getId());

        User userSavedDeleted = userRepository.findById(userSavedDto.getId()).get();
        Assertions.assertEquals(0, userSavedDeleted.getActive());
    }

    @Test
    public void loadUserByUsername()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertDoesNotThrow( () -> userService.loadUserByUsername(userSavedDto.getEmail()) );
        UserDetails userDetails = userService.loadUserByUsername(userSavedDto.getEmail());
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(userSavedDto.getEmail(), userDetails.getUsername());
    }

    @Test
    public void loadUserByUsernameNonExistent()
    {
        UserDto userSavedDto = userService.save(new UserDto(
                "Pedro",
                "Perez",
                "emailgenerico1@email.com",
                "contraseña1",
                "http://imagen1.gl",
                roleMapper.RoleToRoleDto(roleRepository.findById(1).get())
        ));

        Assertions.assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("non_existent@email.com"));
    }
}
