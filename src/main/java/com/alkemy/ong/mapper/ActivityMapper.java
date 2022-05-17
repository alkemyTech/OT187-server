package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.entity.Activity;
import com.alkemy.ong.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityDto activityToActivityDto(Activity activity);

    Activity activityDtoToActivity(ActivityDto activityDto);
    List<ActivityDto> listActivityToListActivityDto(List<Activity>list);
    List<Activity> listActivityDtoToListActivity(List<ActivityDto>list);
}
