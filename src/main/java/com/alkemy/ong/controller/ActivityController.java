package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.ActivityService;
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

    @PostMapping
    public ResponseEntity<ActivityDto> createActivity(@RequestBody ActivityDto activityDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.createActivity(activityDto));
    }

    @PutMapping(REQUEST_ID)
    public ResponseEntity<ActivityDto> updateActivity(@PathVariable Long id, @RequestBody ActivityDto activityDto) {
        return ResponseEntity.ok().body(activityService.updateActivity(id, activityDto));
    }
}
