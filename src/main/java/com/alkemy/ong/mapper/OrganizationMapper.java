package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

    OrganizationMapper organizationMapper = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto organizationToOrganizationDto(Organization organization);

    Organization organizationDtoToOrganization(OrganizationDto dto);

    Organization organizationPublicDtoToOrganization(OrganizationPublicDto organizationPublicDto);

    OrganizationPublicDto organizationToOrganizationPublicDto(Organization organization);

}
