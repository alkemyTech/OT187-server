package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.entity.Slide;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SlideMapper {

    @Mapping(target = "image", ignore = true)
    SlideDto SlideToSlideDto(Slide Slide);

    @Mapping(target = "image", ignore = true)
    Slide SlideDtoToSlide(SlideDto SlideDto);
}