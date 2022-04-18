package com.alkemy.ong.service;

import com.alkemy.ong.entity.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}
