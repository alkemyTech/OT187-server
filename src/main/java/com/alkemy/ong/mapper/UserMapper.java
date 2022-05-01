package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    List<UserDto> allUserstoAllUsersDto (List<User> users);
    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);
    
}
