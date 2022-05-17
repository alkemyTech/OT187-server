package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.NotFoundException;

import java.util.List;

public interface ActivityService {

    ActivityDto createActivity(ActivityDto activityDto);
    ActivityDto updateActivity(Long id, ActivityDto activityDto) throws NotFoundException;

    List<ActivityDto> getAllActivities();

    void deleteActivity(Long id);

}

