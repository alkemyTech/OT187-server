package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.entity.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    OrganizationDto organizationToOrganizationDto(Organization organization);

    Organization organizationDtoToOrganization(OrganizationDto organizationDto);

    List<OrganizationSlimDto> organizationsToOrganizationsSlimDto(List<Organization> organization);

    OrganizationSlimDto organizationToOrganizationSlimDto(Organization organization);

}
