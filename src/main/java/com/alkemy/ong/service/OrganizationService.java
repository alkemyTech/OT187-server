package com.alkemy.ong.service;


import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.entity.Organization;

import java.util.List;

public interface OrganizationService {

  List<Organization> getAllOrganizations();
  Organization findOrganizationByName(String name);
  OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto);
}
