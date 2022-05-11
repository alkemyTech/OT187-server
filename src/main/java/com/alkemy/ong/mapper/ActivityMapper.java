package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityDto activityToActivityDto(Activity activity);

    Activity activityDtoToActivity(ActivityDto activityDto);
}
