package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    RoleRepository roleRepository;
    
    public User convertToEntity(UserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
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
    
    
//        entity.setRoleId(id_received.get());

}
