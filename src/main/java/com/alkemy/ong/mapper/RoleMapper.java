package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.RoleDto;
import com.alkemy.ong.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto RoleToRoleDto(Role role);
    Role RoleDtoToRole(RoleDto RoleDto);
}
