package com.alkemy.ong.auth.mapper;

import com.alkemy.ong.auth.domain.RoleDomain;
import com.alkemy.ong.entity.Role;
public class RoleMapper {

    public static RoleDomain roleEntityToRoleDomain(Role role) {
        RoleDomain roleDomain = RoleDomain.builder().
                id(role.getId()).
                name(role.getName()).
                description(role.getDescription()).
                timestamps(role.getTimestamps()).build();
        return roleDomain;
    }

    public static Role roleDomainToRoleEntity(RoleDomain roleDomain) {
        Role role = Role.builder().
                id(roleDomain.getId()).
                name(roleDomain.getName()).
                description(roleDomain.getDescription()).
                timestamps(roleDomain.getTimestamps()).build();
        return role;
    }
}
