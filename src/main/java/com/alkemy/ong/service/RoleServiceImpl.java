package com.alkemy.ong.service;

import com.alkemy.ong.entity.Role;
import com.alkemy.ong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        roleRepository.deleteById(id);
    }
}
