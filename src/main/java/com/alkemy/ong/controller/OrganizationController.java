package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.service.OrganizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/organization/public")
@AllArgsConstructor
public class OrganizationController {
    @Autowired
    private final OrganizationMapper mapStructMapper;
    @Autowired
    private final OrganizationServiceImpl organizationService;


    @GetMapping()
    public ResponseEntity<List<OrganizationSlimDto>> getAllOrganizations(){
        return new ResponseEntity<>(mapStructMapper.organizationToOrganizationSlimDto(organizationService.getAllOrganizations()), HttpStatus.OK);
    }


}
