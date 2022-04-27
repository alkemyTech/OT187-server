package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;

public interface ActivityService {

    ActivityDto createActivity(ActivityDto activityDto);

    ActivityDto updateActivity(Long id, ActivityDto activityDto);

}