package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.entity.Organization;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationServiceImpl;
import com.alkemy.ong.service.SlideServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.alkemy.ong.utility.Constantes.*;

@RestController
@RequestMapping(ORGANIZATION_MAP_REQUEST)
@AllArgsConstructor
public class OrganizationController {

    @Autowired
    private  OrganizationServiceImpl organizationService;


    @GetMapping(ORGANIZATION_ALL)
    public ResponseEntity<List<OrganizationSlimDto>> getAllOrganizations(){
      return new ResponseEntity<>(organizationService.getAllOrganizations(), HttpStatus.OK);
    }
    @GetMapping(ORGANIZATION_ID)
    public ResponseEntity<OrganizationDto> getOrganizationsById(@RequestParam ("id") Long id){
        return new ResponseEntity<>(organizationService.getOrganizationById(id), HttpStatus.OK);
    }

    @GetMapping(REQUEST_NAME)
    public ResponseEntity<OrganizationSlimDto> getOrganizationByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(organizationService.findOrganizationByName(name),HttpStatus.OK);
    }

    @PostMapping(REQUEST_ID)
    public ResponseEntity<OrganizationDto> updateOrganization (@PathVariable Long id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.ok().body(organizationService.updateOrganization(id, organizationDto));
    }

}
