package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.service.OrganizationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import static com.alkemy.ong.utility.Constantes.*;

@RestController
@RequestMapping(value = ORGANIZATION_MAP_REQUEST)
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

    @GetMapping(ORGANIZATION_GET_NAME)
    public ResponseEntity<OrganizationSlimDto> getOrganizationByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(mapStructMapper.organizationToOrganizationSlimDto(organizationService.findOrganizationByName(name)),HttpStatus.OK);
    }

    @PostMapping(ORGANIZATION_UPDATE)
    public ResponseEntity<OrganizationDto> updateOrganization (@PathVariable Long id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.ok().body(organizationService.updateOrganization(id, organizationDto));
    }

}
