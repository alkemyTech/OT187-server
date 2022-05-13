package com.alkemy.ong.service;


import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.entity.Organization;

import java.util.List;

public interface OrganizationService {

  List<OrganizationSlimDto> getAllOrganizations();
  OrganizationSlimDto findOrganizationByName(String name);
  OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto);
  OrganizationDto getOrganizationById(Long id);
}
