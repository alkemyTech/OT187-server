package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.exception.InvalidDTOException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityRepository activityRepository;

    @Transactional

    @Override
    public ActivityDto createActivity(ActivityDto activityDto) throws InvalidDTOException {

        if (activityDto.getName() == null || activityDto.getName().isEmpty()) {
            throw new InvalidDTOException("Activity name is required");
        }

        if (activityDto.getContent() == null || activityDto.getContent().isEmpty()) {
            throw new InvalidDTOException("Activity content is required");
        }

        Activity activity = activityMapper.activityDtoToActivity(activityDto);

        return activityMapper.activityToActivityDto(activityRepository.save(activity));
    }
    
    @Transactional
    @Override
    public ActivityDto updateActivity(Long id, ActivityDto activityDto) {

        Activity activity = activityRepository.findById(id).orElseThrow(() -> new NotFoundException("Activity not found"));

        activity.setId(id);
        activity.setName(activityDto.getName());
        activity.setContent(activityDto.getContent());
        activity.setImage(activityDto.getContent());

        return activityMapper.activityToActivityDto(activityRepository.save(activity));
    }
}

