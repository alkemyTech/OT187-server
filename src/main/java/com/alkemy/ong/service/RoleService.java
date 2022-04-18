package com.alkemy.ong.service;

import com.alkemy.ong.entity.Role;

public interface RoleService {

    Role findById(Integer id);

    Role save(Role role);

    void deleteById(Integer id);
}
