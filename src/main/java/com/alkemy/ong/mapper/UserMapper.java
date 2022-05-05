package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.Role;
import com.alkemy.ong.entity.User;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    List<UserDto> allUserstoAllUsersDto (List<User> users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User updateUserFromDto(UserDto userDto, @MappingTarget User user);

    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);


}
