package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationSlimDto;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.service.OrganizationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all organizations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Return a list of organizations",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrganizationSlimDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @GetMapping()
    public ResponseEntity<List<OrganizationSlimDto>> getAllOrganizations(){
        return new ResponseEntity<>(mapStructMapper.organizationsToOrganizationsSlimDto(organizationService.getAllOrganizations()), HttpStatus.OK);
    }

    @Operation(summary = "Get organization by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201200", description = "Successful operation. Return a organization",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrganizationSlimDto.class))}),
            @ApiResponse(responseCode = "400", description = "Name not found",
                    content = @Content)})
    @GetMapping(REQUEST_NAME)
    public ResponseEntity<OrganizationSlimDto> getOrganizationByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(mapStructMapper.organizationToOrganizationSlimDto(organizationService.findOrganizationByName(name)),HttpStatus.OK);
    }

    @Operation(summary = "Upadate an organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation. Organization updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ActivityDto.class))}),
            @ApiResponse(responseCode = "500", description = "Dto is not valid",
                    content = @Content)})
    @PostMapping(REQUEST_ID)
    public ResponseEntity<OrganizationDto> updateOrganization (@PathVariable Long id, @RequestBody OrganizationDto organizationDto) {
        return ResponseEntity.ok().body(organizationService.updateOrganization(id, organizationDto));
    }

}
