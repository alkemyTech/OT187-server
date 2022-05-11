package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.entity.Organization;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private  OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;


    @Autowired
    private SlideService slideService;



    @Override
    public List<OrganizationSlimDto> getAllOrganizations() {
        return organizationMapper.organizationsToOrganizationsSlimDto(organizationRepository.findAll());
    }

    @Override
    public OrganizationDto getOrganizationById(Long id){
        return organizationMapper.organizationToOrganizationDto(organizationRepository.findById(id).orElseThrow());

    }

    @Override
    public OrganizationSlimDto findOrganizationByName(String name){
        return organizationMapper.organizationToOrganizationSlimDto(organizationRepository.findByName(name));
    }

    @Override
    public OrganizationDto updateOrganization(Long id, OrganizationDto organizationDto) {

        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if(organizationOptional.isPresent()) {

            Organization organization = organizationOptional.get();
            organization.setName(organizationDto.getName());
            organization.setImage(organizationDto.getImage());
            organization.setAddress(organizationDto.getAddress());
            organization.setPhone(organizationDto.getPhone());
            organization.setEmail(organizationDto.getEmail());
            organization.setWelcomeText(organizationDto.getWelcomeText());
            organization.setAboutUsText(organizationDto.getAboutUsText());

            return organizationMapper.organizationToOrganizationDto(organizationRepository.save(organization));
        } else {
            throw new NotFoundException("Organization not found");
        }

    }


}
