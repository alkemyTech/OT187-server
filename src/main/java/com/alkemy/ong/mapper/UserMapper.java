package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.User;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {
    
    public User convertToEntity(UserDto dto) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhoto(dto.getPhoto());
        entity.setRoleId(dto.getRoleId());
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
}
