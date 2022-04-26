package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.EmailExistsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User convertToEntity(UserDto dto) throws EmailExistsException {
        if (emailExist(dto.getEmail())) {
            throw new EmailExistsException("An account with the email address "
                    + dto.getEmail() + " already exists.");
        }
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setPhoto(dto.getPhoto());
        entity.setRoleId(dto.getRoleId());
        entity.setRoleId(lookForRole(dto.getReceivedRoleId()));
        entity.setCreationDate(dto.getCreationDate());
        return entity;
    }
    
    public UserDto convertToDTO(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhoto(entity.getPhoto());
        dto.setRoleId(entity.getRoleId());
        dto.setCreationDate(entity.getCreationDate());
        return dto;
    }
    
    private Role lookForRole(Integer id) {
        Role role = new Role();
        if (id != null) {
            Optional<Role> foundedRole = roleRepository.findById(id);
            if (foundedRole.isPresent()) {
                role = foundedRole.get();
            }
        } else {
            throw new NotFoundException("Invalid Role");
        }
        return role;
    }
    
    private boolean emailExist(String emailAccount) {
        Optional<User> userFound = userRepository.findByEmail(emailAccount);
        return userFound.isPresent();
    }
}
