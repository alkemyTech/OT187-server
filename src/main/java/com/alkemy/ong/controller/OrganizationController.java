package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.service.OrganizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/organization/public")
@AllArgsConstructor
public class OrganizationController {
    @Autowired
    private  OrganizationMapper mapStructMapper;
    @Autowired
    private  OrganizationServiceImpl organizationService;


    @GetMapping()
    public ResponseEntity<List<OrganizationSlimDto>> getAllOrganizations(){
        return new ResponseEntity<>(mapStructMapper.organizationsToOrganizationsSlimDto(organizationService.getAllOrganizations()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<OrganizationSlimDto> getOrganizationByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(mapStructMapper.organizationToOrganizationSlimDto(organizationService.findOrganizationByName(name)),HttpStatus.OK);


    }


}
