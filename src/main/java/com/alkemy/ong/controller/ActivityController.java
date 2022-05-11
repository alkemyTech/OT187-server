package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.utility.Constantes.ACTIVITY_URL;
import static com.alkemy.ong.utility.Constantes.REQUEST_ID;


@RestController
@RequestMapping(ACTIVITY_URL)
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Operation(summary = "Create a new activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation. Activity created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ActivityDto.class))}),
            @ApiResponse(responseCode = "500", description = "Dto is not valid",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activityDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.createActivity(activityDto));
    }

    @Operation(summary = "Update an activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation. Activity updated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ActivityDto.class))}),
            @ApiResponse(responseCode = "400", description = "Activity not found",
                    content = @Content)})
    @PutMapping(REQUEST_ID)
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long id, @RequestBody ActivityDto activityDto) {
        return ResponseEntity.ok().body(activityService.updateActivity(id, activityDto));
    }
}
