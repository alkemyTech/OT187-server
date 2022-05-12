package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.service.OrganizationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

import static com.alkemy.ong.utility.Constantes.*;

@Tag(name = "Organization")
@RestController
@RequestMapping(value = ORGANIZATION_MAP_REQUEST)
@AllArgsConstructor
public class OrganizationController {

    @Autowired
    private  OrganizationMapper mapStructMapper;
    @Autowired
    private  OrganizationServiceImpl organizationService;

    @Operation(summary = "Find an organization by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<OrganizationSlimDto>> getAllOrganizations(){
        return new ResponseEntity<>(mapStructMapper.organizationsToOrganizationsSlimDto(organizationService.getAllOrganizations()), HttpStatus.OK);
    }

    @Operation(summary = "Find an organization by name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization found succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class)) })
    })
    @GetMapping(REQUEST_NAME)
    public ResponseEntity<OrganizationSlimDto> getOrganizationByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(mapStructMapper.organizationToOrganizationSlimDto(organizationService.findOrganizationByName(name)),HttpStatus.OK);
    }

    @Operation(summary = "Update an organization")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization updated succesfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrganizationDto.class)) }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Organization not found")
    })
    @PostMapping(REQUEST_ID)
    public ResponseEntity<OrganizationDto> updateOrganization (@PathVariable Long id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.ok().body(organizationService.updateOrganization(id, organizationDto));
    }

}
